package org.example.phaze2.model.portConnectingDetails;

import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Port;

public class Connection {
    private Port toPort;
    private Port fromPort;
    private Curve curve;
    public Connection (Curve curve , Port from, Port to) {

        this.curve = curve;
        this.fromPort = from;
        this.toPort = to;
    }
    public void ChangePorts(PortTypes portType) {
        toPort.getPortInfo().setType(portType);
        fromPort.getPortInfo().setType(portType);
        toPort.setShape(portType.createShape());
        fromPort.setShape(portType.createShape());
    }
    public void resetIt(){
        PortTypes beforeChange = toPort.getPortInfo().getBeforeChange();
        toPort.getPortInfo().setType(beforeChange);
        fromPort.getPortInfo().setType(beforeChange);
        toPort.setShape(beforeChange.createShape());
        fromPort.setShape(beforeChange.createShape());

    }


    public Curve getCurve() {
        return curve;
    }

    public void setCurve(Curve curve)  {
        this.curve = curve;
    }

    public Port getToPort() {
        return toPort;
    }

    public void setToPort(Port toPort) {
        this.toPort = toPort;
    }

    public Port getFromPort() {
        return fromPort;
    }

    public void setFromPort(Port fromPort) {
        this.fromPort = fromPort;
    }
}
