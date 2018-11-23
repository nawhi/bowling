import java.util.regex.Pattern;

public class FrameList {
    private final String[] frames;

    public FrameList(String scorecard) {
        this.frames = splitToFrames(scorecard);
    }

    private static String[] splitToFrames(String scorecard) {
        return scorecard
                .replaceAll(Pattern.quote("||"), "|")
                .split(Pattern.quote("|"));
    }

    public String[] asArray() {
        return frames;
    }
}
