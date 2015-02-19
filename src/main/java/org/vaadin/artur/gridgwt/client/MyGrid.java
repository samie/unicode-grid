package org.vaadin.artur.gridgwt.client;

import com.vaadin.client.renderers.HtmlRenderer;
import com.vaadin.client.renderers.Renderer;
import com.vaadin.client.widget.grid.RendererCellReference;
import com.vaadin.client.widgets.Grid;

public class MyGrid extends Grid<Integer> {

    private Renderer<String> unicodeRenderer = new HtmlRenderer() {

        @Override
        public void render(RendererCellReference cell, String data) {
            int charCode = cell.getColumnIndex() + cell.getRowIndex()
                    * 16;
            super.render(cell, "<span class=\"char\">&#x" + (Integer.toString(charCode, 16)) + ";" + "</span>");
        }
    };

    public MyGrid() {
        super();
        setWidth("100%");
        setHeight("300px");
        setSelectionMode(SelectionMode.NONE);

        // Row header
        addColumn(new Column<String, Integer>("") {

            @Override
            public String getValue(Integer arg0) {
                return null;
            }

        }).setRenderer(new HtmlRenderer() {

            @Override
            public void render(RendererCellReference cell, String data) {
                String rowIndex = Integer.toString(cell.getRowIndex(), 16);
                while (rowIndex.length() < 4) {
                    rowIndex = "0" + rowIndex;
                }
                super.render(cell, "<span  class=\"index\">" + rowIndex + "</span>");
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
