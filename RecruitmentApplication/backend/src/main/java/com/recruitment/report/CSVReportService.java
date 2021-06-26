package com.recruitment.report;

import org.springframework.stereotype.Service;

import static com.recruitment.report.ReportType.CSV;

@Service
public class CSVReportService implements ReportService {
    @Override
    public String export() {
        return "I am a CSV reporter.";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
