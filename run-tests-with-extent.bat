@echo off
echo ========================================
echo Running Insider Test Automation with ExtentReports
echo ========================================

echo.
echo Step 1: Running tests...
call mvn clean test

echo.
echo Step 2: Opening ExtentReports...
echo Reports are generated in: test-output\ExtentReports\
echo.

REM Try to open the report
if exist "test-output\ExtentReports\Insider Test Automation Report.html" (
    echo Opening report: Insider Test Automation Report.html
    start "" "test-output\ExtentReports\Insider Test Automation Report.html"
) else (
    echo No ExtentReports found in test-output\ExtentReports\
)
:end

echo.
echo ========================================
echo Test execution completed!
echo Check test-output\ExtentReports\ for detailed reports
echo ========================================
pause
