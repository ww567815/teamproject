package com.team.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Covid19DTO {
	
	private double accDefRate;
	private int accExamCnt;
	private int accExamCompCnt;
	private int careCnt;
	private int clearCnt;
	private Date createDt;
	private int deathCnt;
	private int decideCnt;
	private int examCnt;
	private int resutlNegCnt;
	private int seq;
	private String stateDt;
	private String stateTime;
	private Date updateDt;
}
