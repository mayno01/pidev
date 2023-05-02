/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class StatisticController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private AnchorPane rootPane;

@Override
public void initialize(URL url, ResourceBundle rb) {
    int numBlockedUsers = countBlockedUsers();
    int numNonBlockedUsers = countNonBlockedUsers();

    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    ObservableList<XYChart.Data<String, Integer>> data = FXCollections.observableArrayList();
    data.add(new XYChart.Data<>("Blocked", numBlockedUsers));
    data.add(new XYChart.Data<>("Not Blocked", numNonBlockedUsers));
    series.setData(data);

    barChart.getData().add(series);
}


 private int countBlockedUsers() {
    int count = 0;
    try {
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT COUNT(*) as count FROM user WHERE blocked = 1";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return count;
}


private int countNonBlockedUsers() {
    int count = 0;
    try {
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT COUNT(*) AS count FROM user WHERE blocked = 0";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return count;
}

}

