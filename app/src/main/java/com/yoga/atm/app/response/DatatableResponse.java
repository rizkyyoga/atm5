package com.yoga.atm.app.response;

import java.util.ArrayList;
import java.util.List;

public class DatatableResponse {
	private Long draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<List<String>> data;

	public DatatableResponse() {
	}

	public DatatableResponse(Long draw) {
		this.draw = draw;
		this.recordsFiltered = 0L;
		this.recordsTotal = 0L;
		this.data = new ArrayList<List<String>>();
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

}