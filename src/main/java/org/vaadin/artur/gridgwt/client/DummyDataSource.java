package org.vaadin.artur.gridgwt.client;

import com.vaadin.client.data.DataChangeHandler;
import com.vaadin.client.data.DataSource;

public class DummyDataSource implements DataSource<Integer> {

	private DataChangeHandler dataChangeHandler;
	private int size;

	public DummyDataSource(int size) {
		this.size = size;
	}

	@Override
	public void ensureAvailability(int firstRowIndex, int numberOfRows) {
		dataChangeHandler.dataAvailable(firstRowIndex, numberOfRows);
	}

	@Override
	public Integer getRow(int rowIndex) {
		return rowIndex;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void setDataChangeHandler(DataChangeHandler dataChangeHandler) {
		this.dataChangeHandler = dataChangeHandler;

	}

	@Override
	public com.vaadin.client.data.DataSource.RowHandle<Integer> getHandle(
			Integer row) {
		return null;
	}

	@Override
	public int indexOf(Integer row) {
		return row;
	}

}
