package com.recruitment.report;

import org.springframework.stereotype.Service;

import static com.recruitment.report.ReportType.PDF;

@Service
public class PdfReportService implements ReportService {
    @Override
    public String export() {
        return "I am a PDF reporter.";
    }


    @Override
    public ReportType getType() {
        return PDF;
    }
}
