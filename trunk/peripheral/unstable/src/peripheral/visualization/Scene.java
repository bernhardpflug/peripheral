package peripheral.visualization;

import java.awt.Image;
import peripheral.logic.positioningtool.PositioningTool;

public class Scene {

    private java.util.List<PositioningTool> tools;

    private Image theme;

    public Scene () {
    }

    public Image getTheme () {
        return theme;
    }

    public void setTheme (Image val) {
        this.theme = val;
    }

    public java.util.List<PositioningTool> getTools () {
        return tools;
    }

    public void setTools (java.util.List<PositioningTool> val) {
        this.tools = val;
    }

}

