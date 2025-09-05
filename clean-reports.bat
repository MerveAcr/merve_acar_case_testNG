@echo off
echo ========================================
echo Cleaning Old ExtentReports
echo ========================================

echo.
echo Removing old report files...

if exist "test-output\ExtentReports\" (
    del /q "test-output\ExtentReports\*.html" 2>nul
    if %errorlevel% equ 0 (
        echo Old reports cleaned successfully!
    ) else (
        echo No old reports found to clean.
    )
) else (
    echo Reports directory does not exist.
)

echo.
echo ========================================
echo Cleanup completed!
echo ========================================
pause
