package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathologyController implements Initializable {


    @FXML
    private TableView<Pathology> PathologyTable;
    @FXML
    private TableColumn<Pathology, String> TestID;
    @FXML
    private TableColumn<Pathology, String> TestName;
    @FXML
    private TableColumn<Pathology, String> TestPrice;
    @FXML
    private TableColumn<Pathology, String> Testdescription;
    @FXML
    private Button selectButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button backButton;

    @FXML
    private Button PathologyConfirmButton;

    @FXML
    private Label errorMessage;
    ObservableList<Pathology> testList = FXCollections.observableArrayList();

    public static String chosenID  = null;


    public void searchPathology(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchPathologyQuery = "SELECT `ID`, `Test Name`, `Test Description`, `Price` FROM hospital.pathologyinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchPathologyQuery);

            while(queryOutput.next()){
                String testID = queryOutput.getString("ID");
                String testname = queryOutput.getString("Test Name");
                String testDescription = queryOutput.getString("Test Description");
                Double testprice = queryOutput.getDouble("Price");

                testList.add(new Pathology(testID,testname,testDescription,testprice));
            }


            TestID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            TestName.setCellValueFactory(new PropertyValueFactory<>("testName"));
            Testdescription.setCellValueFactory(new PropertyValueFactory<>("testDescription"));
            TestPrice.setCellValueFactory(new PropertyValueFactory<>("testPrice"));

            PathologyTable.setItems(testList);

            //filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchPathology();

//        selectButton.setOnAction( e -> {
//            // Add to cart and update bill
//        });
//        removeButton.setOnAction(( e -> {
//            Pathology selectedtest = PathologyTable.getSelectionModel().getSelectedItem();
//            if (selectedtest != null) {
//                selectedtest.removePathology(errorMessage,e);
//            }
//            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Choose Lab Test");
//        }));

        PathologyConfirmButton.setOnAction( e -> {

        // Add to cart and update billing
        });


        backButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "ReceptionScreen.fxml","Reception");
        });



    }
}
