package com.ss.model;

public class Publisher {
	
	private Integer publisherId;
	private String publisherName;
	private String publisherAddress;
	
	public Publisher() {}
	
	public Publisher(Integer p_id, String p_name, String p_address)
	{
		publisherId = p_id;
		publisherName = p_name;
		publisherAddress = p_address;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer p_id) {
		publisherId = p_id;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String p_name) {
		publisherName = p_name;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String p_address) {
		publisherAddress = p_address;
	}

}
