package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListnerSetUp implements ITestListener {
    ExtentReports extentReports;
    ExtentSparkReporter spark;
    ExtentTest test;

    public void configureReports() {
        extentReports = new ExtentReports();
        spark = new ExtentSparkReporter("target/Spark.html");
        extentReports.attachReporter(spark);
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    public void onStart(ITestContext context) {
        configureReports();
    }

    public void onTestSuccess(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel("Test Passed: " + result.getName(), ExtentColor.GREEN));
        test.assignAuthor("Atul Ambade patil");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));
        test.assignAuthor("Atul Ambade");
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped: " + result.getName(), ExtentColor.BROWN));
        test.assignAuthor("Atul Ambade");
    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }


    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
