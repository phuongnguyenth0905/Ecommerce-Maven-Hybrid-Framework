package commons;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import java.util.function.Function;
import java.util.stream.Collectors;
import utilitiesConfig.RandomDataUtils;
import org.apache.logging.log4j.Logger;

public class BasePage {
    JavascriptExecutor jsExecutor;
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    private static final Logger log = LogManager.getLogger(BasePage.class);

    public static BasePage getBasePage() {
        return new BasePage();
    }
    /* ================== BROWSER ACTIONS ================== */
    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }
    /* ================== ALERT ================== */
    public void waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }
    /* ================== WINDOW / TAB ================== */
    public void switchToWindownByID(WebDriver driver, String parentID) {
        Set<String> allWindowns = driver.getWindowHandles();
        for (String runWindow : allWindowns) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindownByTitle(WebDriver driver, String title) {
        Set<String> allWindowns = driver.getWindowHandles();
        for (String runWindow : allWindowns) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowns = driver.getWindowHandles();
        for (String runWindow : allWindowns) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    /* ================== LOCATOR / ELEMENT CORE ================== */
    public By getByXpath(String locator) {
        return By.xpath(locator);

    }

    public WebElement getWebElement(WebDriver driver, String locator) {

        return driver.findElement(By.xpath(locator));
    }
    public WebElement getWebElement(WebDriver driver, String locator, String... values) {

        String dynamicXpath = String.format(locator, (Object[]) values);

        return driver.findElement(By.xpath(dynamicXpath));
}
    public List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public String getDynamicLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);

    }
    /* ================== CLICK / SENDKEYS / DROPDOWN ================== */
    private void clickToElement(WebElement element) {
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        getWebElement(driver, locator).click();
    }
    public void clickToElement(WebDriver driver, String locator, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        getWebElement(driver, getDynamicLocator(locator, values)).click();
    }
    public void sendKeyToElement(WebDriver driver, String locator, String value) {
        highlightElement(driver, locator);
        WebElement element = getWebElement(driver, locator);
        element.click(); // focus vào input
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        WebElement element = getWebElement(driver, getDynamicLocator(locator, values));
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        highlightElement(driver, locator);
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        Select select = new Select(getWebElement(driver, getDynamicLocator(locator, values)));
        select.selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
                                           String expectedItem) {
        highlightElement(driver, childItemLocator);
        clickToElement(driver, parentLocator);
        RandomDataUtils.sleepInSecond(0.5);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
        List<WebElement> allItems = getListWebElement(driver, childItemLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                RandomDataUtils.sleepInSecond(0.5);
                clickToElement(item);
                RandomDataUtils.sleepInSecond(0.5);
                break;
            }
        }
    }
    /* ================== GET TEXT / ATTRIBUTE / COUNT ================== */
    public String getElementText(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        return getWebElement(driver, locator).getText().trim();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        return getWebElement(driver, getDynamicLocator(locator, values)).getText().trim();
    }

    public String getElementAttributeValue(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        return getWebElement(driver, locator).getAttribute("value");
    }

    public String getElementAttributeByName(WebDriver driver, String locator, String attributeName) {
        highlightElement(driver, locator);
        return getWebElement(driver, locator).getAttribute(attributeName);
    }
    public String getElementAttributeByName(WebDriver driver, String locator, String attributeName, String... values) {
        highlightElement(driver,  getDynamicLocator(locator, values));
        return getWebElement(driver,  getDynamicLocator(locator, values)).getAttribute(attributeName);
    }

    public int getElementNumber(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        return getListWebElement(driver, locator).size();
    }

    public int getElementNumber(WebDriver driver, String locator, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        return getListWebElement(driver, getDynamicLocator(locator, values)).size();
    }
    /* ================== CHECKBOX / RADIO ================== */
    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }
    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String...values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        WebElement element = getWebElement(driver, getDynamicLocator(locator, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        WebElement element = getWebElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }
    // element biến mất sau khi hiển thị
    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        //System.out.println("Start time" + new Date().toString());
        log.info("Start time {}",new Date());
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, locator);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0) {
           // System.out.println("Element not in DOM");
            log.warn("Element not in DOM: {}", locator);
            //System.out.println("End time" + new Date().toString());
            log.info("End time {}", new Date());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            //System.out.println("Element in DOM but not visible/ displayed");
            log.warn("Element in DOM but NOT visible: {}", locator);
            //System.out.println("End time" + new Date().toString());
            log.info("End time {}", new Date());
            return true;
        } else {
            //System.out.println("Element in DOM and visible");
            log.info("Element in DOM and visible: {}", locator);
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
        //System.out.println("Start time" + new Date().toString());
        log.info("Start time {}", new Date());
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicLocator(locator, values));
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0) {
           // System.out.println("Element not in DOM");
            log.warn("Element not in DOM: {}", locator);
            //System.out.println("End time" + new Date().toString());
            log.info("End time {}", new Date());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            //System.out.println("Element in DOM but not visible/ displayed");
            log.warn("Element in DOM but NOT visible: {}", locator);
           // System.out.println("End time" + new Date().toString());
            log.info("End time {}", new Date());
            return true;
        } else {
           // System.out.println("Element in DOM and visible");
            log.info("Element in DOM and visible: {}", locator);
            return false;
        }
    }
    //có / không có trong DOM (ẩn)
    public boolean isElementPresentOrVisible(WebDriver driver, String locator) {
        try {
            return getWebElement(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //Tồn tại trong DOM. Không quan tâm hiển thị(ktra element hidden)
    public boolean isElementPresent(WebDriver driver, String locator) {
        try {
            getWebElement(driver, locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /* ================== ELEMENT STATE HELPERS (4 CASES) ================== */
    //element tồn tại và hiển thị trong DOM
    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
        return getWebElement(driver, getDynamicLocator(locator, values)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }
    public boolean isElementSelected(WebDriver driver, String locator, String... values) {
        return getWebElement(driver, getDynamicLocator(locator, values)).isSelected();
    }
    /* ================== FRAME / IFRAMES ================== */
    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }
    /* ================== ACTIONS (HOVER / DRAG / KEY) ================== */
    public void hoverToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }
    public void hoverToElement(WebDriver driver, String locator, String... values){
        highlightElement(driver, getDynamicLocator(locator, values));
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, getDynamicLocator(locator, values))).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void presskeyToElement(WebDriver driver, String locator, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void presskeyToElement(WebDriver driver, String locator, Keys key, String... values) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicLocator(locator, values)), key).perform();
    }
    /* ================== JAVASCRIPT EXECUTOR ================== */
    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor
                .executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void scrollToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }
    public void scrollToElement(WebDriver driver, String locator, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicLocator(locator, values)));
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location='" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
                "border: 2px solid red; border-style: dashed;");
        RandomDataUtils.sleepInSecond(0.4);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }
    public void highlightElement(WebDriver driver, String locator, String... values) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, getDynamicLocator(locator, values));
        js.executeScript(
                "arguments[0].setAttribute('style', 'border: 2px solid red;');",
                element
        );
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... values) {
        highlightElement(driver, getDynamicLocator(locator, values));
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locator, values)));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        highlightElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value','" + value + "')", getWebElement(driver, locator));
    }

    public void removeAttributeINDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getWebElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }

            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");

            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
                getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
        if (status) {
            return true;
        }
        return false;
    }
    /* ================== WAIT HELPERS ================== */
    public void waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
            }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
         }

    public void waitForListElementVisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
    }
    public void waitForElementPresence(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
    }
    /* ================== UPLOAD FILE ================== */
    public void uploadMultipleFiles(WebDriver driver,String uploadInputLocator, String... fileNames) {
        String filePath = GlobalConstants.PROJECT_PATH + getDirectorySlash("uploadFiles");
        StringBuilder fullPaths = new StringBuilder();

        for (String file : fileNames) {
            fullPaths.append(filePath).append(file).append("\n");
        }

        fullPaths = new StringBuilder(fullPaths.toString().trim());

        sendKeyToElement(driver, uploadInputLocator, fullPaths.toString());
    }

    public String getDirectorySlash(String folderName) {
        String separator = System.getProperty("file.separator");
        return separator + folderName + separator;
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
    /* ================== SORT HELPERS (STRING / NUMBER / DATE) ================== */
    //SORT DYNAMIC
    public <T extends Comparable<? super T>> boolean isDataSorted(
            WebDriver driver,
            String xpathLocator,
            Function<String, T> converter,
            boolean ascending) {

        List<WebElement> elements = driver.findElements(By.xpath(xpathLocator));
        if (elements == null || elements.isEmpty()) {
            //System.out.println("[WARN] No elements found for locator: " + xpathLocator);
            log.warn("No elements found for locator: {}", xpathLocator);
            return false;
        }

        // Lấy dữ liệu UI -> List<T>
        List<T> uiList;
        try {
            uiList = elements.stream()
                    .map(e -> converter.apply(e.getText().trim()))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            //System.out.println("[ERROR] Convert failed: " + ex.getMessage());
            log.error("Convert failed: {}", ex.getMessage());
            return false;
        }

        // In dữ liệu UI (giống style cũ)
       // System.out.println("----------- Data on UI -----------");
        log.debug("----------- Data on UI -----------");
        //uiList.forEach(System.out::println);
        log.debug("UI Data: {}", uiList);

        // Tạo bản sorted ASC trong code
        List<T> sortedList = new ArrayList<>(uiList);
        Collections.sort(sortedList);

        if (ascending) {
            // In đúng cho trường hợp ASC
            //System.out.println("-----------  SORT ASC data in Code -----------");
            log.debug("----------- SORT ASC data in Code -----------");
           // sortedList.forEach(System.out::println);
            log.debug("Sorted Data ASC: {}", sortedList);
            return uiList.equals(sortedList);
        } else {
            // Nếu cần DESC: đảo chiều của sortedList (từ ASC -> DESC)
            Collections.reverse(sortedList);
            //System.out.println("-----------  SORT DESC data in Code -----------");
            log.debug("----------- SORT DESC data in Code -----------");
            //sortedList.forEach(System.out::println);
            log.debug("Sorted Data DESC: {}", sortedList);
            return uiList.equals(sortedList);
        }
    }

    // ----------------------- Wrappers tiện dụng ------------------------
    // String (case-insensitive)
    public boolean isStringSortedAsc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> s.toLowerCase(Locale.ROOT), true);
    }
    public boolean isStringSortedDesc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> s.toLowerCase(Locale.ROOT), false);
    }

    // Float / Number (loại bỏ $ , % ký tự không phải số)
    public boolean isFloatSortedAsc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> {
            String cleaned = s.replaceAll("[^0-9\\.-]", ""); // giữ số, dấu - và .
            return cleaned.isEmpty() ? 0f : Float.parseFloat(cleaned);
        }, true);
    }
    public boolean isFloatSortedDesc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> {
            String cleaned = s.replaceAll("[^0-9\\.-]", "");
            return cleaned.isEmpty() ? 0f : Float.parseFloat(cleaned);
        }, false);
    }

    // Integer
    public boolean isIntegerSortedAsc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> {
            String cleaned = s.replaceAll("[^0-9\\-]", "");
            return cleaned.isEmpty() ? 0 : Integer.parseInt(cleaned);
        }, true);
    }
    public boolean isIntegerSortedDesc(WebDriver driver, String xpathLocator) {
        return isDataSorted(driver, xpathLocator, s -> {
            String cleaned = s.replaceAll("[^0-9\\-]", "");
            return cleaned.isEmpty() ? 0 : Integer.parseInt(cleaned);
        }, false);
    }

    // Date (mẫu mặc định: "MMM dd yyyy" như "May 12 2023", bạn có thể tạo thêm wrapper nếu format khác)
    public boolean isDateSortedAsc(WebDriver driver, String xpathLocator, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return isDataSorted(driver, xpathLocator, s -> LocalDate.parse(s.replace(",", "").trim(), fmt), true);
    }
    public boolean isDateSortedDesc(WebDriver driver, String xpathLocator, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return isDataSorted(driver, xpathLocator, s -> LocalDate.parse(s.replace(",", "").trim(), fmt), false);
    }

    public Date convertStringToDate(String dateInString) {
        dateInString = dateInString.replace(",", "");
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatDate.parse(dateInString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
