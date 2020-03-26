package Design;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Candy extends Applet
{public BranchGroup createBranchGroup()
{BranchGroup BranchGroupRoot =new BranchGroup();
    BoundingSphere bounds=new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    Color3f bgColor=new Color3f(1.0f,1.0f,1.0f);
    Background bg=new Background(bgColor);
    bg.setApplicationBounds(bounds);
    BranchGroupRoot.addChild(bg);
    Color3f directionalColor=new Color3f(1.f,0.f,0.f);
    Vector3f vec=new Vector3f(0.f,0.f,-1.0f);
    DirectionalLight directionalLight=new DirectionalLight(directionalColor,vec);
    directionalLight.setInfluencingBounds(bounds);
    BranchGroupRoot.addChild(directionalLight);
    TransformGroup transformgroup=new TransformGroup();
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
    transformgroup.addChild(new SphereTriangleArrayDisplay());
    transformgroup.addChild(new SphereQuadArrayDisplay());
    transformgroup.addChild(new SurfaceDisplay());
    transformgroup.addChild(new SurfaceDisplay2());
    BranchGroupRoot.compile();
    return BranchGroupRoot;
}

    public  Candy() {
        setLayout(new BorderLayout());

        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);

        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D c=new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene=createBranchGroup();
        SimpleUniverse u=new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }
    public static void main(String[] args) {
        new MainFrame(new Candy(),400,400);}
}
class SphereTriangleArrayDisplay extends Shape3D
{SphereTriangleArrayDisplay()
{//定义两个TriangleArray数组
    float theta1,theta2;//等分角
    float R=0.3f;//球体半径
    int i,j,k;
    int nn1=20,nn2=50;//对球体表面的纵向与横向等分点数
    TriangleArray Trianglesurface1 =new TriangleArray(nn2*3,TriangleArray.COORDINATES|TriangleArray.NORMALS);
    TriangleArray Trianglesurface2 =new TriangleArray(nn2*3,TriangleArray.COORDINATES|TriangleArray.NORMALS);
//定义存放球体体表数据点的数组
    float[][][] spherexyz=new float[100][200][3];
    theta1=(float)Math.PI/nn1;//纵向分角
    theta2=2.0f*(float)Math.PI/nn2;//横向分角
//球体表坐标点计算
    for(i=0;i<nn1+1;i++)
        for(j=0;j<nn2+1;j++)
        {spherexyz[i][j][0]=R*(float)Math.sin(i*theta1)*(float)Math.cos(j*theta2);

            spherexyz[i][j][1]=R*(float)Math.cos(i*theta1);
            spherexyz[i][j][2]=R*(float)Math.sin(i*theta1)*(float)Math.sin(j*theta2);}
    int c=0;//以顶点数累加的方式设置点的序号
    Point3f A01=new Point3f(spherexyz[0][0][0],
            spherexyz[0][0][1],spherexyz[0][0][2]);
    for(j=0;j<nn2;j++)
    {Point3f A1=new Point3f(spherexyz[1][j][0],
            spherexyz[1][j][1],spherexyz[1][j][2]);
        Point3f A2=new Point3f(spherexyz[1][j+1][0],
                spherexyz[1][j+1][1],spherexyz[1][j+1][2]);
        Vector3f a = new Vector3f(A1.x - A01.x, A1.y - A01.y, A1.z - A01.z);
        Vector3f b = new Vector3f(A2.x - A01.x, A2.y - A01.y, A2.z - A01.z);
        Vector3f n = new Vector3f();
        n.cross(a, b);
        n.normalize();
//设置点的序号及坐标
        Trianglesurface1.setCoordinate(c, A01);
        Trianglesurface1.setCoordinate(c+1, A1);
        Trianglesurface1.setCoordinate(c+2, A2);
//设置点的法向量
        Trianglesurface1.setNormal(c, n);
        Trianglesurface1.setNormal(c+1, n);
        Trianglesurface1.setNormal(c+2, n);
        c=c+3;}
    c=0;
    Point3f A02=new   Point3f(spherexyz[nn1][0][0], spherexyz[nn1][0][1],spherexyz[nn1][0][2]);
    for(j=0;j<nn2;j++)
    {Point3f A1=new Point3f(spherexyz[nn1-1][j][0],spherexyz[nn1-1][j][1],spherexyz[nn1-1][j][2]);
        Point3f A2=new Point3f(spherexyz[nn1-1][j+1][0],spherexyz[nn1-1][j+1][1],spherexyz[nn1-1][j+1][2]);
        Vector3f a = new Vector3f(A1.x - A02.x, A1.y - A02.y, A1.z - A02.z);
        Vector3f b = new Vector3f(A2.x - A02.x, A2.y - A02.y, A2.z - A02.z);
        Vector3f n = new Vector3f();
        n.cross(a, b);
        n.normalize();
//设置点的序号及坐标
        Trianglesurface2.setCoordinate(c, A02);
        Trianglesurface2.setCoordinate(c+1, A1);
        Trianglesurface2.setCoordinate(c+2, A2);
//设置点的法向量
        Trianglesurface2.setNormal(c, n);
        Trianglesurface2.setNormal(c+1, n);
        Trianglesurface2.setNormal(c+2, n);
        c=c+3;
    }
    this.addGeometry(Trianglesurface1);
    this.addGeometry(Trianglesurface2);
    this.setAppearance(createAppearance0());
}
    Appearance createAppearance0()
    {//指定外观
        PolygonAttributes polygona=new PolygonAttributes();
        polygona.setCullFace(PolygonAttributes.CULL_NONE);
        polygona.setBackFaceNormalFlip(true);
//polygona.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(polygona);
        Material material = new Material();
        Color3f red = new Color3f(1.0f, 0.f, 0.0f);
        material.setDiffuseColor(red);
        appearance.setMaterial(material);
        return appearance;
    }}

class SphereQuadArrayDisplay extends Shape3D
{SphereQuadArrayDisplay()
{//计算球面上点的x,y,z坐标
    int nn1=20,nn2=50;
    float theta1,theta2;
    float R=0.3f;
    int i,j,k;
    float[][][] spherexyz=new float[100][200][3];
    theta1=(float)Math.PI/nn1;
    theta2=2.0f*(float)Math.PI/nn2;
    for(i=0;i<nn1+1;i++)
        for(j=0;j<nn2+1;j++)
        {spherexyz[i][j][0]=R*(float)Math.sin(i*theta1)*(float)Math.cos(j*theta2);
            spherexyz[i][j][1]=R*(float)Math.cos(i*theta1);
            spherexyz[i][j][2]=R*(float)Math.sin(i*theta1)*(float)Math.sin(j*theta2);}
    QuadArray Quadsurface =new QuadArray((nn1-2)*nn2*4,GeometryArray.COORDINATES|GeometryArray.NORMALS);
    int c=0;//以顶点数累加的方式设置数组中顶点的序号
    for(i=1;i<nn1-1;i++)

    {for(j=0;j<nn2;j++)
    {//设置一个四边形上的4个点坐标值
        Point3f A=new Point3f(spherexyz[i][j][0],spherexyz[i][j][1],spherexyz[i][j][2]);
        Point3f B=new Point3f(spherexyz[i][j+1][0],
                spherexyz[i][j+1][1],spherexyz[i][j+1][2]);
        Point3f C=new Point3f(spherexyz[i+1][j+1][0],
                spherexyz[i+1][j+1][1],spherexyz[i+1][j+1][2]);
        Point3f D=new Point3f(spherexyz[i+1][j][0],
                spherexyz[i+1][j][1],spherexyz[i+1][j][2]);
//计算四边形的法向量
        Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
        Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);

        Vector3f n = new Vector3f();
        n.cross(b, a);
        n.normalize();
//设置点的序号
        Quadsurface.setCoordinate(c, A);
        Quadsurface.setCoordinate(c+1, B);
        Quadsurface.setCoordinate(c+2, C);
        Quadsurface.setCoordinate(c+3, D);
//按序号设置点的法向量
        Quadsurface.setNormal(c, n);
        Quadsurface.setNormal(c+1, n);
        Quadsurface.setNormal(c+2, n);
        Quadsurface.setNormal(c+3, n);
        c=c+4;
    }
        this.addGeometry(Quadsurface);
        this.setAppearance(createAppearance0());
    }}
    Appearance createAppearance0()
    {//指定外观
        PolygonAttributes polygona=new PolygonAttributes();
        polygona.setCullFace(PolygonAttributes.CULL_NONE);
        polygona.setBackFaceNormalFlip(true);
        //polygona.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(polygona);
        Material material = new Material();
        Color3f red = new Color3f(1.0f, .0f, .0f);
        //material.setAmbientColor(red);
        //material.setSpecularColor(red);
        //material.setDiffuseColor(red);
        //material.setEmissiveColor(red);
        material.setShininess(50.f);
        appearance.setMaterial(material);
        return appearance;}}

class SurfaceDisplay extends Shape3D {
    SurfaceDisplay() {
        this.setGeometry(createGeometry0());
        this.setAppearance(createAppearance0());
    }

    float[][][] SurfacePointsxyz = new float[5][51][3];

    Geometry createGeometry0() {
        int i, j, k, c;
        int n0 = 20;
        float theta;
        //定义在xoy平面内的旋转线，旋转轴为y轴
        float[] Xp0 = {0.1f, 0.15f, 0.2f, 0.25f, 0.3f};
        float[] Yp0 = {0.27f, 0.35f, 0.4f, 0.45f, 0.5f};
        //计算对圆周n0等分后所得的旋转角
        theta = 2.f * (float) Math.PI / n0;
        //计算旋转后旋转平面上点的x、y、z坐标值
        for (i = 0; i < 5; i++)
            for (j = 0; j < n0 + 1; j++) {
                SurfacePointsxyz[i][j][0] = Xp0[i] * (float) Math.cos(theta * j);
                SurfacePointsxyz[i][j][1] = Yp0[i];
                SurfacePointsxyz[i][j][2] = Xp0[i] * (float) Math.sin(theta * j);
            }
        //按顺时针方向设置每个四边形的坐标值，求法向量
        QuadArray SurfaceQuadArray = new QuadArray(5 * n0 * 4, GeometryArray.COORDINATES | GeometryArray.NORMALS);
        c = 0;
        //该变量用来对顶点按顺序编号，该编号是连续的，不能重复\
        for (i = 0; i < 4; i++) {
            for (j = 0; j < n0; j++) {
                Point3f A = new Point3f(SurfacePointsxyz[i][j][0], SurfacePointsxyz[i][j][1], SurfacePointsxyz[i][j][2]);
                Point3f B = new Point3f(SurfacePointsxyz[i + 1][j][0], SurfacePointsxyz[i + 1][j][1], SurfacePointsxyz[i + 1][j][2]);
                Point3f C = new Point3f(SurfacePointsxyz[i + 1][j + 1][0], SurfacePointsxyz[i + 1][j + 1][1], SurfacePointsxyz[i + 1][j + 1][2]);
                Point3f D = new Point3f(SurfacePointsxyz[i][j + 1][0], SurfacePointsxyz[i][j + 1][1], SurfacePointsxyz[i][j + 1][2]);
                //计算4个点的法向量，使法向量指向体外
                Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
                Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
                Vector3f n = new Vector3f();
                n.cross(b, a);
                n.normalize();
                //设置点的序号
                SurfaceQuadArray.setCoordinate(c, A);
                SurfaceQuadArray.setCoordinate(c + 1, B);
                SurfaceQuadArray.setCoordinate(c + 2, C);
                SurfaceQuadArray.setCoordinate(c + 3, D);
                //设置点的序号所对应的法向量
                SurfaceQuadArray.setNormal(c, n);
                SurfaceQuadArray.setNormal(c + 1, n);
                SurfaceQuadArray.setNormal(c + 2, n);
                SurfaceQuadArray.setNormal(c + 3, n);
                c = c + 4;
            }
        }
        return SurfaceQuadArray;
    }

    Appearance createAppearance0() {//指定外观，这样才有明暗效果
        PolygonAttributes polygona = new PolygonAttributes();
        //有了下面这两行语句，在有法向量的情况下,可使面的两侧都能显示
        polygona.setBackFaceNormalFlip(true);
        polygona.setCullFace(PolygonAttributes.CULL_NONE);
        //polygona.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        //polygona.setPolygonMode(PolygonAttributes.POLYGON_POINT);
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(polygona);
        Material material = new Material();
        Color3f white = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f red = new Color3f(.0f, 0.0f, 1.0f);
        material.setDiffuseColor(white);
        //material.setSpecularColor(red);
        //material.setShininess(20.0f);
        appearance.setMaterial(material);
        return appearance;
    }
}

class SurfaceDisplay2 extends Shape3D {
    SurfaceDisplay2() {
        this.setGeometry(createGeometry0());
        this.setAppearance(createAppearance0());
    }

    float[][][] SurfacePointsxyz = new float[5][51][3];

    Geometry createGeometry0() {
        int i, j, k, c;
        int n0 = 20;
        float theta;
        //定义在xoy平面内的旋转线，旋转轴为y轴
        float[] Xp0 = {0.1f, 0.15f, 0.2f, 0.25f, 0.3f};
        float[] Yp0 = {-0.27f, -0.35f, -0.4f, -0.45f, -0.5f};
        //计算对圆周n0等分后所得的旋转角
        theta = 2.f * (float) Math.PI / n0;
        //计算旋转后旋转平面上点的x、y、z坐标值
        for (i = 0; i < 5; i++)
            for (j = 0; j < n0 + 1; j++) {
                SurfacePointsxyz[i][j][0] = Xp0[i] * (float) Math.cos(theta * j);
                SurfacePointsxyz[i][j][1] = Yp0[i];
                SurfacePointsxyz[i][j][2] = Xp0[i] * (float) Math.sin(theta * j);
            }
        //按顺时针方向设置每个四边形的坐标值，求法向量
        QuadArray SurfaceQuadArray = new QuadArray(5 * n0 * 4, GeometryArray.COORDINATES | GeometryArray.NORMALS);
        c = 0;
        //该变量用来对顶点按顺序编号，该编号是连续的，不能重复\
        for (i = 0; i < 4; i++) {
            for (j = 0; j < n0; j++) {
                Point3f A = new Point3f(SurfacePointsxyz[i][j][0], SurfacePointsxyz[i][j][1], SurfacePointsxyz[i][j][2]);
                Point3f B = new Point3f(SurfacePointsxyz[i + 1][j][0], SurfacePointsxyz[i + 1][j][1], SurfacePointsxyz[i + 1][j][2]);
                Point3f C = new Point3f(SurfacePointsxyz[i + 1][j + 1][0], SurfacePointsxyz[i + 1][j + 1][1], SurfacePointsxyz[i + 1][j + 1][2]);
                Point3f D = new Point3f(SurfacePointsxyz[i][j + 1][0], SurfacePointsxyz[i][j + 1][1], SurfacePointsxyz[i][j + 1][2]);
                //计算4个点的法向量，使法向量指向体外
                Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
                Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
                Vector3f n = new Vector3f();
                n.cross(b, a);
                n.normalize();
                //设置点的序号
                SurfaceQuadArray.setCoordinate(c, A);
                SurfaceQuadArray.setCoordinate(c + 1, B);
                SurfaceQuadArray.setCoordinate(c + 2, C);
                SurfaceQuadArray.setCoordinate(c + 3, D);
                //设置点的序号所对应的法向量
                SurfaceQuadArray.setNormal(c, n);
                SurfaceQuadArray.setNormal(c + 1, n);
                SurfaceQuadArray.setNormal(c + 2, n);
                SurfaceQuadArray.setNormal(c + 3, n);
                c = c + 4;
            }
        }
        return SurfaceQuadArray;
    }

    Appearance createAppearance0() {//指定外观，这样才有明暗效果
        PolygonAttributes polygona = new PolygonAttributes();
        //有了下面这两行语句，在有法向量的情况下,可使面的两侧都能显示
        polygona.setBackFaceNormalFlip(true);
        polygona.setCullFace(PolygonAttributes.CULL_NONE);
        //polygona.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        //polygona.setPolygonMode(PolygonAttributes.POLYGON_POINT);
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(polygona);
        Material material = new Material();
        Color3f white = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f red = new Color3f(.0f, 0.0f, 1.0f);
        material.setDiffuseColor(white);
        //material.setSpecularColor(red);
        //material.setShininess(20.0f);
        appearance.setMaterial(material);
        return appearance;
    }
}


