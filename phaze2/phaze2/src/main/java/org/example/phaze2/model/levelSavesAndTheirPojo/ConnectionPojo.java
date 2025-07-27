package org.example.phaze2.model.levelSavesAndTheirPojo;

public class ConnectionPojo {

    private PortPojo fromPort;

    private PortPojo toPort;

    private CurvePojo curve;

    public PortPojo getFromPort() {
        return fromPort;
    }

    public void setFromPort(PortPojo fromPort) {
        this.fromPort = fromPort;
    }

    public PortPojo getToPort() {
        return toPort;
    }

    public void setToPort(PortPojo toPort) {
        this.toPort = toPort;
    }

    public CurvePojo getCurve() {
        return curve;
    }

    public void setCurve(CurvePojo curve) {
        this.curve = curve;
    }
}
