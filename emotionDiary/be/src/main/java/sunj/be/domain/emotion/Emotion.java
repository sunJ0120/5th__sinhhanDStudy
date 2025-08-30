package sunj.be.domain.emotion;

public enum Emotion {
    VERYGOOD("매우 좋음"),
    GOOD("좋음"),
    SOSO("보통"),
    BAD("나쁨"),
    VERYBAD("매우 나쁨");

    private final String label;

    Emotion(String label){
        this.label = label;
    }

    // UI에 내려줄때 STRING으로 내려주기 위함이다.
    public String getLabel(){
        return label;
    }
}
