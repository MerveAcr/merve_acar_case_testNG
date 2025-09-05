#!/bin/bash

echo "========================================"
echo "Running Insider Test Automation with ExtentReports"
echo "========================================"

echo ""
echo "Step 1: Running tests..."
mvn clean test

echo ""
echo "Step 2: Opening ExtentReports..."
echo "Reports are generated in: test-output/ExtentReports/"
echo ""

# Try to open the report
if [ -f "test-output/ExtentReports/Insider Test Automation Report.html" ]; then
    echo "Opening report: Insider Test Automation Report.html"
    if command -v xdg-open > /dev/null; then
        xdg-open "test-output/ExtentReports/Insider Test Automation Report.html"
    elif command -v open > /dev/null; then
        open "test-output/ExtentReports/Insider Test Automation Report.html"
    else
        echo "Please open the report manually: test-output/ExtentReports/Insider Test Automation Report.html"
    fi
else
    echo "No ExtentReports found in test-output/ExtentReports/"
fi

echo ""
echo "========================================"
echo "Test execution completed!"
echo "Check test-output/ExtentReports/ for detailed reports"
echo "========================================"
