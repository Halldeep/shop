package com.oracle.maket.model.javaben;

import java.io.Serializable;

/**
 * 订单详细表 儌僨儖僋儔僗.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Detailorders implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 订单详细表编号. */
	private Integer itemsid;

	/** 商品编号. */
	private Integer goodsid;

	/** 商品数量. */
	private Integer goodsnumber;

	/** 订单编号. */
	private Integer ordersid;

	/**
	 * 僐儞僗僩儔僋僞.
	 */
	public Detailorders() {
	}

	/**
	 * 订单详细表编号 傪愝掕偟傑偡.
	 * 
	 * @param itemsid
	 *            订单详细表编号
	 */
	public void setItemsid(Integer itemsid) {
		this.itemsid = itemsid;
	}

	/**
	 * 订单详细表编号 傪庢摼偟傑偡.
	 * 
	 * @return 订单详细表编号
	 */
	public Integer getItemsid() {
		return this.itemsid;
	}

	/**
	 * 商品编号 傪愝掕偟傑偡.
	 * 
	 * @param goodsid
	 *            商品编号
	 */
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	/**
	 * 商品编号 傪庢摼偟傑偡.
	 * 
	 * @return 商品编号
	 */
	public Integer getGoodsid() {
		return this.goodsid;
	}

	/**
	 * 商品数量 傪愝掕偟傑偡.
	 * 
	 * @param goodsnumber
	 *            商品数量
	 */
	public void setGoodsnumber(Integer goodsnumber) {
		this.goodsnumber = goodsnumber;
	}

	/**
	 * 商品数量 傪庢摼偟傑偡.
	 * 
	 * @return 商品数量
	 */
	public Integer getGoodsnumber() {
		return this.goodsnumber;
	}

	/**
	 * 订单编号 傪愝掕偟傑偡.
	 * 
	 * @param ordersid
	 *            订单编号
	 */
	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}

	/**
	 * 订单编号 傪庢摼偟傑偡.
	 * 
	 * @return 订单编号
	 */
	public Integer getOrdersid() {
		return this.ordersid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemsid == null) ? 0 : itemsid.hashCode());

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Detailorders other = (Detailorders) obj;
		if (itemsid == null) {
			if (other.itemsid != null) {
				return false;
			}
		} else if (!itemsid.equals(other.itemsid)) {
			return false;
		}

		return true;
	}

}
