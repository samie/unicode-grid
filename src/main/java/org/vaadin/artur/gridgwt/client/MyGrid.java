package org.vaadin.artur.gridgwt.client;

import com.vaadin.client.data.DataChangeHandler;
import com.vaadin.client.data.DataSource;
import com.vaadin.client.renderers.Renderer;
import com.vaadin.client.widget.grid.RendererCellReference;
import com.vaadin.client.widgets.Grid;

public class MyGrid extends Grid<Integer> {
	private Renderer<String> unicodeRenderer = new Renderer<String>() {

		@Override
		public void render(RendererCellReference cellRef, String arg1) {
			int charCode = cellRef.getColumnIndex() + cellRef.getRowIndex()
					* 16;
			cellRef.getElement().setInnerHTML(
					"&#x" + (Integer.toString(charCode, 16)) + ";");
		}
	};

	public MyGrid() {
		super();
		setWidth("800px");
		setSelectionMode(SelectionMode.SINGLE);

		// Row header
		addColumn(new Column<String, Integer>("") {

			@Override
			public String getValue(Integer arg0) {
				return null;
			}

		}).setRenderer(new Renderer<String>() {

			@Override
			public void render(RendererCellReference arg0, String arg1) {
				arg0.getElement().setInnerText(
						Integer.toString(arg0.getRowIndex(), 16));

			}
		}).setWidth(60);
		for (int i = 0; i < 10; i++) {
			addColumn(new Column<String, Integer>("" + i) {
				@Override
				public String getValue(Integer row) {
					return "";
				}

			}).setRenderer(unicodeRenderer);
		}
		for (char i = 'A'; i <= 'F'; i++) {
			addColumn(new Column<String, Integer>("" + i) {
				@Override
				public String getValue(Integer row) {
					return "";
				}

			}).setRenderer(unicodeRenderer);
		}

		// Set a dummy data source which only defines the number of rows
		setDataSource(new DummyDataSource(0xffff));
	}
}
