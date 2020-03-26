package lab1;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class example3_2 extends Applet{
    public BranchGroup createBranchGroup() {
        BranchGroup BranchGroupRoot = new BranchGroup();                             //BranchGroup
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);  //创建球心坐标在0.0.0  半径为100 ?
        Color3f bgColor = new Color3f(1.0f, 1.0f, 1.0f);                               //Color3f(r,g,b)
        Background bg = new Background(bgColor);                                     //Background
        bg.setApplicationBounds(bounds);
        BranchGroupRoot.addChild(bg);                                                // BranchGroup addChild (Background)

        TransformGroup transformgroup = new TransformGroup();
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BranchGroupRoot.addChild(transformgroup);

        MouseRotate mouserotate = new MouseRotate();
        mouserotate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mouserotate);
        mouserotate.setSchedulingBounds(bounds);

        //////////////////////////////////////////////////////
        MouseZoom mousezoom = new MouseZoom();
        mousezoom.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousezoom);
        mousezoom.setSchedulingBounds(bounds);
        /////////////////////////////////////////////////////这好像没什么用

        MouseTranslate mousetranslate = new MouseTranslate();
        mousetranslate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousetranslate);
        mousetranslate.setSchedulingBounds(bounds);
        Shape3D shapepoints = new Shape3D();
        //定义顶点坐标
        float vertexes[] = {0.5f, 0.6f, 0.0f, -0.5f, 0.6f, 0.0f,
                0.5f, 0.05f, 0.0f, -0.5f, 0.05f, 0.0f,
                -0.5f, -0.7f, 0.0f, 0.5f, -0.7f, 0.1f,};
        //定义点颜色
        float pointcolors[] = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,};
        int vCount = 6;
        PointArray points = new PointArray(vCount, PointArray.COORDINATES | PointArray.COLOR_3);
        points.setCoordinates(0, vertexes);
        points.setColors(0, pointcolors);
        Appearance app = new Appearance();
        //定义点的属性
        PointAttributes pointsattributes = new PointAttributes();
        //定义点的大小
        pointsattributes.setPointSize(70.0f);
        //如有下面这项，空间点显示为圆球形；如无，空间点显示为正方形
        pointsattributes.setPointAntialiasingEnable(true);
        app.setPointAttributes(pointsattributes);
        shapepoints.setGeometry(points);
        shapepoints.setAppearance(app);
        transformgroup.addChild(shapepoints);
        BranchGroupRoot.compile();
        return BranchGroupRoot;
    }

    public example3_2() {
        setLayout(new BorderLayout());
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene = createBranchGroup();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }

    public static void main(String[] args) {
        new MainFrame (new example3_1(),300,300);
    }

}

