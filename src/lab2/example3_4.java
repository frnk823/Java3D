package lab2;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class example3_4 extends Applet{
    public BranchGroup createBranchGroupSceneGraph(){
        BranchGroup BranchGroupRoot = new BranchGroup();                             //BranchGroup
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);  //创建球心坐标在0.0.0  半径为100 ?
        Color3f bgColor = new Color3f(1.0f,1.0f,1.0f);                               //Color3f(r,g,b)
        Background bg = new Background(bgColor);                                     //Background
        bg.setApplicationBounds(bounds);
        BranchGroupRoot.addChild(bg);                                                // BranchGroup addChild (Background)
        Color3f directionalColor = new Color3f(1.0f,0.0f,0.0f);
        Vector3f vec = new Vector3f(0.0f,0.0f,-1.0f);
        DirectionalLight directionalLight = new DirectionalLight(directionalColor,vec);
        directionalLight.setInfluencingBounds(bounds);
        BranchGroupRoot.addChild(directionalLight);
        TransformGroup transformgroup = new TransformGroup();
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BranchGroupRoot.addChild(transformgroup);
        MouseRotate mouserotate = new MouseRotate();
        mouserotate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mouserotate);
        mouserotate.setSchedulingBounds(bounds);
        MouseZoom mousezoom = new MouseZoom();
        mousezoom.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousezoom);
        mousezoom.setSchedulingBounds(bounds);
        MouseTranslate mousetranslate = new MouseTranslate();
        mousetranslate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousetranslate);
        mousetranslate.setSchedulingBounds(bounds);
        transformgroup.addChild(shapelines());//将线加入到TransformGroup中
        BranchGroupRoot.compile();
        return BranchGroupRoot;
    }

    public Shape3D shapelines() {
        Shape3D shapelines0 = new Shape3D();
        //定义点坐标
        float vertexes[] = {0.8f, 0.5f, 0.0f, -0.8f, 0.8f, 0.0f,
                0.8f, 0.0f, 0.0f, -0.8f, 0.5f, 0.0f,
                0.8f, -0.5f, 0.0f, -0.8f, 0.2f, 0.0f};
        //定义颜色
        float colors[] = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
        //定义线数组
        LineArray lines = new LineArray(6, LineArray.COORDINATES|LineArray.COLOR_3);
        lines.setCoordinates(0, vertexes);
        lines.setColors(0, colors);
        //定义线属性
        LineAttributes lineattributes = new LineAttributes();
        lineattributes.setLineWidth(20.0f);
        lineattributes.setLineAntialiasingEnable(true);
        lineattributes.setLinePattern(LineAttributes.PATTERN_SOLID);
        //PATTERN_SOLID PATTERN_DASH PATTERN_DOT PATTERN_DASH_NOT
        Appearance app = new Appearance();
        app.setLineAttributes(lineattributes);
        shapelines0.setGeometry(lines);
        shapelines0.setAppearance(app);
        return shapelines0;
    }

    public example3_4() {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene = createBranchGroupSceneGraph();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }



    public static void main(String[] args) {
        new MainFrame (new example3_4(),300,300);
    }

}

