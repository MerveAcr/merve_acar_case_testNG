#!/bin/bash

echo "========================================"
echo "Cleaning Old ExtentReports"
echo "========================================"

echo ""
echo "Removing old report files..."

if [ -d "test-output/ExtentReports" ]; then
    rm -f test-output/ExtentReports/*.html
    if [ $? -eq 0 ]; then
        echo "Old reports cleaned successfully!"
    else
        echo "No old reports found to clean."
    fi
else
    echo "Reports directory does not exist."
fi

echo ""
echo "========================================"
echo "Cleanup completed!"
echo "========================================"
