@echo off
echo ========================================
echo Setting up Java and Maven Environment
echo ========================================

echo.
echo Checking for Java installation...

REM Try to find Java in common locations
set JAVA_HOME=
if exist "C:\Program Files\Java\jdk1.8.0_*" (
    for /d %%i in ("C:\Program Files\Java\jdk1.8.0_*") do set JAVA_HOME=%%i
    goto :java_found
)
if exist "C:\Program Files\Java\jdk-8*" (
    for /d %%i in ("C:\Program Files\Java\jdk-8*") do set JAVA_HOME=%%i
    goto :java_found
)
if exist "C:\Program Files\Java\jdk-11*" (
    for /d %%i in ("C:\Program Files\Java\jdk-11*") do set JAVA_HOME=%%i
    goto :java_found
)
if exist "C:\Program Files\Java\jdk-17*" (
    for /d %%i in ("C:\Program Files\Java\jdk-17*") do set JAVA_HOME=%%i
    goto :java_found
)

echo Java not found in common locations.
echo Please install Java 11 or higher and add it to your PATH.
echo Download from: https://adoptium.net/
pause
exit /b 1

:java_found
echo Found Java at: %JAVA_HOME%
set PATH=%JAVA_HOME%\bin;%PATH%

echo.
echo Checking for Maven...

REM Try to find Maven
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven not found in PATH.
    echo Please install Maven and add it to your PATH.
    echo Download from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo Maven found.

echo.
echo ========================================
echo Running Tests with ExtentReports
echo ========================================

call mvn clean test

echo.
echo ========================================
echo Test execution completed!
echo Check test-output\ExtentReports\ for detailed reports
echo ========================================

REM Try to open the latest report
for /f "delims=" %%i in ('dir /b /o-d "test-output\ExtentReports\*.html" 2^>nul') do (
    echo Opening report: %%i
    start "" "test-output\ExtentReports\%%i"
    goto :end
)

echo No ExtentReports found in test-output\ExtentReports\
:end

pause
