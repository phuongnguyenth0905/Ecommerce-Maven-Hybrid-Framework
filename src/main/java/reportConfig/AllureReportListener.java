package reportConfig;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class AllureReportListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(AllureReportListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
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
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("=== ALLURE LISTENER STARTED  ===");
        //String projectRoot = System.getProperty("user.dir"); // đường dẫn project khi chạy Eclipse
        // commons.AllureEnvWriter.writeEnvironment(projectRoot);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("⚠️ SKIPPED: " + iTestResult.getName());
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

}
