package com.onboard.plugin.git.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Diff {

    public static final Logger logger = LoggerFactory.getLogger(Diff.class);
    private static final String LINE_NUMBER_REGEX = "@@ \\-(\\d+)(?:,(\\d+))? \\+(\\d+)(?:,(\\d+))? @@";
    private int f1Start;
    private int f1Count;
    private int f2Start;
    private int f2Count;
    private List<DiffLine> lines;

    public Diff(Diff diff) {
        this.f1Start = diff.getF1Start();
        this.f1Count = diff.getF1Count();
        this.f2Start = diff.getF2Start();
        this.f2Count = diff.getF2Count();
        lines = new ArrayList<DiffLine>();
        for (DiffLine dl : diff.getLines()) {
            lines.add(new DiffLine(dl));
        }
    }

    public class DiffLine {
        private String leftLineNumber;
        private String rightLineNumber;
        private int lineNumber;
        private String line;
        private List<Review> reviews;

        public DiffLine(String line) {
            this.line = line;
            this.leftLineNumber = "";
            this.rightLineNumber = "";
            this.lineNumber = 0;
            reviews = new ArrayList<Review>();
        }

        public DiffLine(DiffLine dl) {
            this.line = dl.getLine();
            this.leftLineNumber = dl.getLeftLineNumber();
            this.rightLineNumber = dl.getRightLineNumber();
            this.lineNumber = dl.getLineNumber();
            reviews = new ArrayList<Review>();
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getLeftLineNumber() {
            return leftLineNumber;
        }

        public void setLeftLineNumber(String leftLineNumber) {
            this.leftLineNumber = leftLineNumber;
        }

        public String getRightLineNumber() {
            return rightLineNumber;
        }

        public void setRightLineNumber(String rightLineNumber) {
            this.rightLineNumber = rightLineNumber;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }
    }

    public static List<Diff> parseDiff(String diffStr) {
        logger.debug(diffStr);
        String[] tmps = diffStr.split(LINE_NUMBER_REGEX);
        Matcher m = Pattern.compile(LINE_NUMBER_REGEX).matcher(diffStr);
        logger.debug("{}", tmps.length);
        List<Diff> ret = new ArrayList<Diff>();
        int lineStart = 0;
        for (String tmp : Arrays.copyOfRange(tmps, 1, tmps.length)) {
            m.find();
            String[] lines = tmp.split("\n");
            logger.debug(String.format("%s %s %s %s", m.group(1), m.group(2), m.group(3), m.group(4)));
            Diff diff = new Diff(m.group(1), m.group(2), m.group(3), m.group(4),
                    Arrays.asList(Arrays.copyOfRange(lines, 1, lines.length)), lineStart);
            ret.add(diff);
            lineStart += diff.getLines().size();
        }

        return ret;
    }

    private Diff(String f1Start, String f1Count, String f2Start,
                 String f2Count, List<String> lines, int lineStart) {
        this.f1Start = Integer.parseInt(f1Start);
        this.f1Count = f1Count == null ? 1 : Integer.parseInt(f1Count);
        this.f2Start = Integer.parseInt(f2Start);
        this.f2Count = f2Count == null ? 1 : Integer.parseInt(f2Count);
        this.lines = new ArrayList<DiffLine>();
        int l = this.f1Start;
        int r = this.f2Start;
        for (String line : lines) {
            DiffLine diff = new DiffLine(line);
            if (line.startsWith("-")) {
                diff.setLeftLineNumber(String.valueOf(l++));
            } else if (line.startsWith("+")) {
                diff.setRightLineNumber(String.valueOf(r++));
            } else {
                diff.setLeftLineNumber(String.valueOf(l++));
                diff.setRightLineNumber(String.valueOf(r++));
            }
            this.lines.add(diff);
        }
    }

    public int getF1Start() {
        return f1Start;
    }

    public void setF1Start(int f1Start) {
        this.f1Start = f1Start;
    }

    public int getF1Count() {
        return f1Count;
    }

    public void setF1Count(int f1Count) {
        this.f1Count = f1Count;
    }

    public int getF2Start() {
        return f2Start;
    }

    public void setF2Start(int f2Start) {
        this.f2Start = f2Start;
    }

    public int getF2Count() {
        return f2Count;
    }

    public void setF2Count(int f2Count) {
        this.f2Count = f2Count;
    }

    public List<DiffLine> getLines() {
        return lines;
    }

    public void setLines(List<DiffLine> lines) {
        this.lines = lines;
    }

}
