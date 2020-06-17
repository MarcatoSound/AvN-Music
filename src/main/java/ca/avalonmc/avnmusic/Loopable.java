package ca.avalonmc.avnmusic;

public abstract class Loopable {

    private Track intro;
    private int introLength;
    private Track loop;
    private int loopLength;

    public Loopable(Track intro, Track loop) {
        this.intro = intro;
        this.loop = loop;
    }

    public Track getIntro() {
        return intro;
    }

    public void setIntro(Track intro) {
        this.intro = intro;
    }

    public Track getLoop() {
        return loop;
    }

    public void setLoop(Track loop) {
        this.loop = loop;
    }
}
