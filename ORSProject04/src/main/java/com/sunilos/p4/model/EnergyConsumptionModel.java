package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.EnergyConsumptionBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class EnergyConsumptionModel extends BaseModel<EnergyConsumptionBean> {

	@Override
	public EnergyConsumptionBean getBean() {
		return new EnergyConsumptionBean();
	}

	@Override
	public long add(EnergyConsumptionBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		EnergyConsumptionBean existbean = findByEnergyCode(bean.getEnergyCode());
		if (existbean != null) {
			throw new DuplicateRecordException("energyCode already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getEnergyCode());
			pstmt.setString(3, bean.getDeviceName());
			pstmt.setString(4, bean.getUnitsConsumed());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
		    e.printStackTrace();   // Console me actual error print hoga
		    try {
		        if (conn != null) {
		            conn.rollback();
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    throw new ApplicationException("Exception : " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(EnergyConsumptionBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		EnergyConsumptionBean existbean = findByEnergyCode(bean.getEnergyCode());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("energyCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET ENERGY_CODE=?, DEVICE_NAME=?, UNITS_CONSUMED=?, STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getEnergyCode());
			pstmt.setString(2, bean.getDeviceName());
			pstmt.setString(3, bean.getUnitsConsumed());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			throw new ApplicationException("Exception : " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getWhereClause(EnergyConsumptionBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getEnergyCode() != null && bean.getEnergyCode().length() > 0) {
				sql.append(" AND ENERGY_CODE like '" + bean.getEnergyCode() + "%'");
			}
		}
		return sql.toString();
	}

	public EnergyConsumptionBean findByEnergyCode(String energyCode) {
		return findByUniqueColumn("ENERGY_CODE", energyCode);
	}

	@Override
	public String getTable() {
		return "st_energy_consumption";
	}
}