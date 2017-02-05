package sample;

/** ==============================================================================
 * File         : some_file.java
 *
 * Current Author: Robert Hewlett
 *
 * Previous Author: None
 *
 * Contact Info: somebody@somewhere.com
 *
 * Purpose :
 *
 * Dependencies: None
 *
 * Modification Log :
 *    --> Created MMM-DD-YYYY (fl)
 *    --> Updated MMM-DD-YYYY (fl)
 *
 * =============================================================================
 */

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.map.MapOptions;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


/** ===========================================================
 * The following block does ....
 * ===========================================================
 */


public class Controller implements Initializable {
    @FXML
    Pane pane;

    @FXML
    SwingNode swingNode;

    ArrayList<EQDataPoint> eqDataPoints;
    GraphicsLayer graphicsLayer;



    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapOptions mapOptions = new MapOptions(MapOptions.MapType.NATIONAL_GEOGRAPHIC, 49, -123.5, 7);
        JMap map = new JMap(mapOptions);

        graphicsLayer = new GraphicsLayer();

        map.getLayers().add(graphicsLayer);


        swingNode.setContent(map);





    }

    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    public void close()
    {
        System.exit(0);
    }

    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    public void load() throws Exception {
        eqDataPoints=new ArrayList<EQDataPoint>();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(pane.getScene().getWindow());

        if(file != null)
        {
            Scanner scanner = new Scanner(file);

            scanner.nextLine();

            String[] dataParts;
            EQDataPoint eqDataPoint;

            while (scanner.hasNext())
            {
                dataParts = scanner.nextLine().split(",");
                eqDataPoint = new EQDataPoint();
                eqDataPoint.longitude = Double.parseDouble(dataParts[4]);
                eqDataPoint.latitude = Double.parseDouble(dataParts[3]);
                eqDataPoint.magnitude = dataParts[6];
                eqDataPoints.add(eqDataPoint);
            }
            System.out.println(eqDataPoints.size());
        }
    }

    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */



    public void add() throws Exception {
        Point point;
        SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(Color.RED, 8, SimpleMarkerSymbol.Style.DIAMOND);
        SpatialReference in = SpatialReference.create(SpatialReference.WKID_WGS84);
        SpatialReference out = SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR);
        for(EQDataPoint eqDataPoint : eqDataPoints)
        {


            /** ===========================================================
             * The following block does ....
             * ===========================================================
             */


            point = new Point(eqDataPoint.longitude, eqDataPoint.latitude);
            Point prjPoint = (Point) GeometryEngine.project(point,in,out);
            graphicsLayer.addGraphic(new Graphic(prjPoint, makeMarker()));

        }
    }


    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */



    public PictureMarkerSymbol makeMarker() throws Exception {
        BufferedImage image = null;
        PictureMarkerSymbol pictureMarkerSymbol = null;
        URL url = this.getClass().getResource("/resources/earthquake.png");
        image = ImageIO.read(url);
        pictureMarkerSymbol = new PictureMarkerSymbol(image);
        return pictureMarkerSymbol;
    }

}
