package models;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Name", "Status"};

	private Object[][] data = new Object[5][5];
	
	private int len = 0;

	public void clear() {
		data = new Object[5][5];
		len = 0;
	}
	public void addPerson(String person, String status) {
		
		boolean okay = true;
		
		for(int i = 0; i < len; i++) {
			String name = (String)data[i][0];
			//System.out.println("IF " + name + " equals " + person + " then ADD");
			if(name.equals(person)) {
				okay = false;
			}
		}
		if(okay) {
			data[len] = new Object[] { person, status};
			len++;
		}
	}
	
	public MyTableModel() {
	//	  data[0] = new Object[] {"Kathy", "Accepted"};
//		    {"John", "* Declined"},
//		    {"Sue", "Accepted"},
//		    {"Jane", "Accepted"},
//		    {"Joe", "Accepted"}};
	}
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
    		return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    
    
}
