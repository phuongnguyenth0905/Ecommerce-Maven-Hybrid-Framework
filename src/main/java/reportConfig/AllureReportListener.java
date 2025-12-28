package reportConfig;

import commons.GlobalConstants;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import reportConfig.AllureEnvWriterReport;

import java.util.List;

public class AllureReportListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(AllureReportListener.class);

    // =============== [ I. HELPER / UTILITY ] =================
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        if (driver == null) return new byte[0];
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Failure log", type = "text/plain")
    private String attachTextLog(String message) {
        return message;
    }

    // Screenshot attachments for Allure
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "Text attachment of {0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
    @Attachment(value = "Test Execution Logs", type = "text/plain")
    private String attachTestNGLogs(ITestResult result) {
        List<String> output = Reporter.getOutput(result);
        StringBuilder logs = new StringBuilder();
        for (String line : output) {
            logs.append(line).append("\n");  // giữ định dạng xuống dòng
        }
        return logs.toString();
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Throwable error = iTestResult.getThrowable();
        String testName = iTestResult.getMethod().getMethodName();

        // Lấy message lỗi gọn nhất
        String shortMessage = "Unknown error";
        if (error != null && error.getMessage() != null) {
            // Lấy chỉ dòng đầu tiên, cắt ngắn nếu quá dài
            shortMessage = error.getMessage().split("\n")[0];
            if (shortMessage.length() > 120) {
                shortMessage = shortMessage.substring(0, 120) + "...";
            }
        }

        // Log ra console gọn gàng
        log.error("❌ FAILED: {}", testName);
        log.error("➡ Reason: {}", shortMessage);
        // ===== ALLURE REPORT =====
        WebDriver driver = extractDriver(iTestResult);
        attachScreenshot(driver);
        attachTextLog("""
                FAILED STEP:
                - Last action: Click checkout
                - Expected: Checkout page displayed
                - Actual: Button not clickable
                """);
        attachHtml("""
                    <div>
                      <p><b style='color:red'>FAILED</b>: %s</p>
                      <p><b>Reason:</b> %s</p>
                    </div>
                """.formatted(testName, shortMessage));
        attachTestNGLogs(iTestResult);

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("=== ALLURE LISTENER STARTED  ===");
        String projectRoot = GlobalConstants.PROJECT_PATH; // đường dẫn project khi chạy Eclipse
        AllureEnvWriterReport.writeEnvironment(projectRoot);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("⚠️ SKIPPED: {}", iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.error("❌ FAILED: " + iTestResult.getName());
        log.error(iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext arg0) {
        log.info("=== ALLURE LISTENER FINISHED  ===");

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n===============================");
        log.info("🚀 START TEST: " + result.getMethod().getMethodName());
        System.out.println("===============================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("✅ PASSED: " + result.getMethod().getMethodName());
        System.out.println("===============================");
    }

    private WebDriver extractDriver(ITestResult result) {
        try {
            Object testObject = result.getInstance();

            // Giả định driver nằm trong BaseTest
            var field = testObject.getClass()
                    .getSuperclass()
                    .getDeclaredField("driver");

            field.setAccessible(true);
            return (WebDriver) field.get(testObject);

        } catch (Exception e) {
            return null;
        }
    }
}
