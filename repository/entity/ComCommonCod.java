package zany.repository.entity;

/**
 * 공통코드
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
public class ComCommonCod {

    private String commGrpCd;   // 공통그룹코드
    private String commCd;      // 공통코드
    private String commNm;      // 공통코드명
    private String dispSeq;     // 진열순번
    private String useYn;       // 사용여부
    private String defaultYn;   // default여부
    private String regrId;      // 등록자ID
    private String regDt;       // 등록일시
    private String updrId;      // 수정자ID
    private String updDt;       // 수정일시
    
    public String getCommGrpCd() {
        return commGrpCd;
    }
    public void setCommGrpCd(String commGrpCd) {
        this.commGrpCd = commGrpCd;
    }
    public String getCommCd() {
        return commCd;
    }
    public void setCommCd(String commCd) {
        this.commCd = commCd;
    }
    public String getCommNm() {
        return commNm;
    }
    public void setCommNm(String commNm) {
        this.commNm = commNm;
    }
    public String getDispSeq() {
        return dispSeq;
    }
    public void setDispSeq(String dispSeq) {
        this.dispSeq = dispSeq;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getDefaultYn() {
        return defaultYn;
    }
    public void setDefaultYn(String defaultYn) {
        this.defaultYn = defaultYn;
    }
    public String getRegrId() {
        return regrId;
    }
    public void setRegrId(String regrId) {
        this.regrId = regrId;
    }
    public String getRegDt() {
        return regDt;
    }
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }
    public String getUpdrId() {
        return updrId;
    }
    public void setUpdrId(String updrId) {
        this.updrId = updrId;
    }
    public String getUpdDt() {
        return updDt;
    }
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ComCommonCode [commGrpCd=" + commGrpCd + ", commCd=" + commCd + ", commNm=" + commNm + ", dispSeq="
            + dispSeq + ", useYn=" + useYn + ", defaultYn=" + defaultYn + ", regrId=" + regrId + ", regDt=" + regDt
            + ", updrId=" + updrId + ", updDt=" + updDt + "]";
    }

}
