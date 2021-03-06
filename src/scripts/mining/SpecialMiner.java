package scripts.mining;

import java.util.ArrayList;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Coordinate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import scripts.mining.locations.Location;
import scripts.mining.locations.special.SpecialLocation;

public class SpecialMiner extends MiningStyle{
	@SuppressWarnings("unused")
	private Location location;

	@Override
	public void onStart(String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rock getOre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocationName() {
		return null;
	}

	@Override
	public Coordinate[] getRockLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	private GridPane content = null;
	@Override
	public GridPane getContentPane(Button startButton) {
		if(content != null)return content;
		content = new GridPane();
		content.setPadding(new Insets(25,3,25,3));
		content.setHgap(1.0);
		content.setVgap(1.0);
		ListView<String> locationList = new ListView<String>(); 
		locationList.setPrefWidth(167);

		FlowPane settings = new FlowPane();
		settings.setStyle("-fx-background-color: -fx-background-dark-hundred; -fx-border-color: -fx-flair; -fx-border-style: solid; -fx-border-width: 1;");
		settings.setPrefWrapLength(165);

		ObservableList<String> items = FXCollections.observableArrayList();

		ArrayList<SpecialLocation> locations = getLocations();
		for (int i = 0; i < locations.size(); i++) {
			items.add(locations.get(i).getName());
		}
		locationList.setItems(items);

		locationList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int index = locationList.getSelectionModel().getSelectedIndex();
				Location loc = locations.get(index);
				location = loc;
				ObservableList<String> items = FXCollections.observableArrayList();

				for(String ore: loc.getOres()){
					items.add(ore);
				}
			}
		});
		
		final String LABEL_STYLE = "-fx-text-fill: -fx-flair-text; -fx-font-size: 15px; -fx-background-color: -fx-flair;";

		Label locLabel = new Label("Locations");
		locLabel.setStyle(LABEL_STYLE);
		locLabel.setAlignment(Pos.CENTER);
		locLabel.setPrefWidth(167);
		
		content.add(locLabel, 0, 0);
		content.add(locationList, 0, 1); 
		
		return content;
	}

	@Override
	public void loadSettings() {
		// TODO Auto-generated method stub
		
	}
	
	private ArrayList<SpecialLocation> getLocations() {
		ArrayList<SpecialLocation> locations = new ArrayList<SpecialLocation>();
		if(Environment.isRS3()){
			locations.add(new scripts.mining.locations.special.LavaFlow());
		}else{
			locations.add(new scripts.mining.locations.special.MotherLoad());
		}
		return locations;
	}
}
