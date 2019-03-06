package shopping;

import java.io.Serializable;

public class weeklyStockBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String meigaraId;
	private int sevenDaysAgo;
	private int sixDaysAgo;
	private int fiveDaysAgo;
	private int FourDaysAgo;
	private int ThreeDaysAgo;
	private int TwoDaysAgo;
	private int OneDayAgo;

	public weeklyStockBean(String meigaraId, int sevenDaysAgo, int sixDaysAgo, int fiveDaysAgo, int fourDaysAgo,
			int threeDaysAgo, int twoDaysAgo, int oneDayAgo) {
		this.meigaraId = meigaraId;
		this.sevenDaysAgo = sevenDaysAgo;
		this.sixDaysAgo = sixDaysAgo;
		this.fiveDaysAgo = fiveDaysAgo;
		this.FourDaysAgo = fourDaysAgo;
		this.ThreeDaysAgo = threeDaysAgo;
		this.TwoDaysAgo = twoDaysAgo;
		this.OneDayAgo = oneDayAgo;
	}

	public weeklyStockBean() {
		this.meigaraId = "";
		this.sevenDaysAgo = 0;
		this.sixDaysAgo = 0;
		this.fiveDaysAgo = 0;
		this.FourDaysAgo = 0;
		this.ThreeDaysAgo = 0;
		this.TwoDaysAgo = 0;
		this.OneDayAgo = 0;
	}

	public String getMeigaraId() {
		return meigaraId;
	}

	public void setMeigaraId(String meigaraId) {
		this.meigaraId = meigaraId;
	}

	public int getSevenDaysAgo() {
		return sevenDaysAgo;
	}

	public void setSevenDaysAgo(int sevenDaysAgo) {
		this.sevenDaysAgo = sevenDaysAgo;
	}

	public int getSixDaysAgo() {
		return sixDaysAgo;
	}

	public void setSixDaysAgo(int sixDaysAgo) {
		this.sixDaysAgo = sixDaysAgo;
	}

	public int getFiveDaysAgo() {
		return fiveDaysAgo;
	}

	public void setFiveDaysAgo(int fiveDaysAgo) {
		this.fiveDaysAgo = fiveDaysAgo;
	}

	public int getFourDaysAgo() {
		return FourDaysAgo;
	}

	public void setFourDaysAgo(int fourDaysAgo) {
		FourDaysAgo = fourDaysAgo;
	}

	public int getThreeDaysAgo() {
		return ThreeDaysAgo;
	}

	public void setThreeDaysAgo(int threeDaysAgo) {
		ThreeDaysAgo = threeDaysAgo;
	}

	public int getTwoDaysAgo() {
		return TwoDaysAgo;
	}

	public void setTwoDaysAgo(int twoDaysAgo) {
		TwoDaysAgo = twoDaysAgo;
	}

	public int getOneDayAgo() {
		return OneDayAgo;
	}

	public void setOneDayAgo(int oneDayAgo) {
		OneDayAgo = oneDayAgo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}