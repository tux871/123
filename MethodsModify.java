package com.example.myproject.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.thirdparty.guava.common.collect.Table;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class MethodsModify {
	private HashSet<TableModel> set = new HashSet<>();
	
	class TableModel {
		FlexTable flexTable;
		public TableModel(FlexTable flexTable) {
			super();
			this.flexTable = flexTable;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
	}
	
	
	
	
	public void addWidgetSimply(FlexTable flexTable, int row, Widget widget) {
		if (set.isEmpty()) {
			TableModel tableModelNew = new TableModel(flexTable);
			set.add(tableModelNew);
			tableModelNew.map.put(row,  0);
			flexTable.setWidget(row, tableModelNew.map.get(row), widget);
			tableModelNew.map.put(row, tableModelNew.map.get(row) + 1);
		} else {
		for (TableModel tableModel : set) {
			if (tableModel.flexTable == flexTable) {
				if (!tableModel.map.containsKey(row)) {
					tableModel.map.put(row, 0);
				}
				flexTable.setWidget(row, tableModel.map.get(row), widget);
				tableModel.map.put(row, tableModel.map.get(row) + 1);
			} else {
				TableModel tableModelNew = new TableModel(flexTable);
				set.add(tableModelNew);
				tableModelNew.map.put(row,  0);
				flexTable.setWidget(row, tableModelNew.map.get(row), widget);
				tableModelNew.map.put(row, tableModelNew.map.get(row) + 1);
			}
		}
		}
	}
}
