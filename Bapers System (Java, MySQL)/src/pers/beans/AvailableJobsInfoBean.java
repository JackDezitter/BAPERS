/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.beans;

/**
 *
 * @author Alia
 */
public class AvailableJobsInfoBean {
	private String jobCode;
	private String jobTitle;
	private long jobCost;

	@Override
	public String toString(){
		return jobTitle;
	}

	public AvailableJobsInfoBean() {

	}
	public AvailableJobsInfoBean(String jobCode, String jobTitle, long jobCost) {
		this.jobCode=jobCode;
		this.jobTitle=jobTitle;
		this.jobCost=jobCost;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public long getJobCost() {
		return jobCost;
	}

	public void setJobCost(long jobCost) {
		this.jobCost = jobCost;
	}

}
