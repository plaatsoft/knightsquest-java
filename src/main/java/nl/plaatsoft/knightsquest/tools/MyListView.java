package nl.plaatsoft.knightsquest.tools;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * The Class MyListView.
 */
public class MyListView extends ListView<Object> {

	/**
	 * Instantiates a new my list view.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param items the items
	 */
	@SuppressWarnings("unchecked")
	public MyListView(int x, int y, int width, int height, @SuppressWarnings("rawtypes") ObservableList items) {

		setLayoutX(x);
		setLayoutY(y);
		setMaxHeight(height);
		setMaxWidth(width);
	
		setItems(items);
	}	
}
