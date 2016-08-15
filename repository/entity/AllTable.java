package zany.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ALL_TABLES database table.
 *
 */
@Entity
@Table(name="ALL_TABLES")
//@NamedQuery(name="AllTable.findAll", query="SELECT a.tableName, a.owner FROM AllTable a")
public class AllTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="TABLE_NAME")
    private String tableName;

	@Column(name="AVG_ROW_LEN")
	private BigDecimal avgRowLen;

	@Column(name="AVG_SPACE")
	private BigDecimal avgSpace;

	@Column(name="AVG_SPACE_FREELIST_BLOCKS")
	private BigDecimal avgSpaceFreelistBlocks;

	@Column(name="BACKED_UP")
	private String backedUp;

	private BigDecimal blocks;

	@Column(name="BUFFER_POOL")
	private String bufferPool;

	@Column(name="\"CACHE\"")
	private String cache;

	@Column(name="CELL_FLASH_CACHE")
	private String cellFlashCache;

	@Column(name="CHAIN_CNT")
	private BigDecimal chainCnt;

	@Column(name="CLUSTER_NAME")
	private String clusterName;

	@Column(name="CLUSTER_OWNER")
	private String clusterOwner;

	@Column(name="COMPRESS_FOR")
	private String compressFor;

	private String compression;

	private String degree;

	private String dependencies;

	private String dropped;

	private String duration;

	@Column(name="EMPTY_BLOCKS")
	private BigDecimal emptyBlocks;

	@Column(name="FLASH_CACHE")
	private String flashCache;

	@Column(name="FREELIST_GROUPS")
	private BigDecimal freelistGroups;

	@Column(name="\"FREELISTS\"")
	private BigDecimal freelists;

	@Column(name="GLOBAL_STATS")
	private String globalStats;

	@Column(name="INITIAL_EXTENT")
	private BigDecimal initialExtent;

	@Column(name="INI_TRANS")
	private BigDecimal iniTrans;

	private String instances;

	@Column(name="IOT_NAME")
	private String iotName;

	@Column(name="IOT_TYPE")
	private String iotType;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_ANALYZED")
	private Date lastAnalyzed;

	private String logging;

	@Column(name="MAX_EXTENTS")
	private BigDecimal maxExtents;

	@Column(name="MAX_TRANS")
	private BigDecimal maxTrans;

	@Column(name="MIN_EXTENTS")
	private BigDecimal minExtents;

	private String monitoring;

	private String nested;

	@Column(name="NEXT_EXTENT")
	private BigDecimal nextExtent;

	@Column(name="NUM_FREELIST_BLOCKS")
	private BigDecimal numFreelistBlocks;

	@Column(name="NUM_ROWS")
	private BigDecimal numRows;

	private String owner;

	private String partitioned;

	@Column(name="PCT_FREE")
	private BigDecimal pctFree;

	@Column(name="PCT_INCREASE")
	private BigDecimal pctIncrease;

	@Column(name="PCT_USED")
	private BigDecimal pctUsed;

	@Column(name="READ_ONLY")
	private String readOnly;

	@Column(name="RESULT_CACHE")
	private String resultCache;

	@Column(name="ROW_MOVEMENT")
	private String rowMovement;

	@Column(name="SAMPLE_SIZE")
	private BigDecimal sampleSize;

	private String secondary;

	@Column(name="SEGMENT_CREATED")
	private String segmentCreated;

	@Column(name="SKIP_CORRUPT")
	private String skipCorrupt;

	private String status;

	@Column(name="TABLE_LOCK")
	private String tableLock;

	@Column(name="TABLESPACE_NAME")
	private String tablespaceName;

	@Column(name="\"TEMPORARY\"")
	private String temporary;

	@Column(name="USER_STATS")
	private String userStats;

	public AllTable() {
	}

	public BigDecimal getAvgRowLen() {
		return this.avgRowLen;
	}

	public BigDecimal getAvgSpace() {
		return this.avgSpace;
	}

	public BigDecimal getAvgSpaceFreelistBlocks() {
		return this.avgSpaceFreelistBlocks;
	}

	public String getBackedUp() {
		return this.backedUp;
	}

	public BigDecimal getBlocks() {
		return this.blocks;
	}

	public String getBufferPool() {
		return this.bufferPool;
	}

	public String getCache() {
		return this.cache;
	}

	public String getCellFlashCache() {
		return this.cellFlashCache;
	}

	public BigDecimal getChainCnt() {
		return this.chainCnt;
	}

	public String getClusterName() {
		return this.clusterName;
	}

	public String getClusterOwner() {
		return this.clusterOwner;
	}

	public String getCompressFor() {
		return this.compressFor;
	}

	public String getCompression() {
		return this.compression;
	}

	public String getDegree() {
		return this.degree;
	}

	public String getDependencies() {
		return this.dependencies;
	}

	public String getDropped() {
		return this.dropped;
	}

	public String getDuration() {
		return this.duration;
	}

	public BigDecimal getEmptyBlocks() {
		return this.emptyBlocks;
	}

	public String getFlashCache() {
		return this.flashCache;
	}

	public BigDecimal getFreelistGroups() {
		return this.freelistGroups;
	}

	public BigDecimal getFreelists() {
		return this.freelists;
	}

	public String getGlobalStats() {
		return this.globalStats;
	}

	public BigDecimal getInitialExtent() {
		return this.initialExtent;
	}

	public BigDecimal getIniTrans() {
		return this.iniTrans;
	}

	public String getInstances() {
		return this.instances;
	}

	public String getIotName() {
		return this.iotName;
	}

	public String getIotType() {
		return this.iotType;
	}

	public Date getLastAnalyzed() {
		return this.lastAnalyzed;
	}

	public String getLogging() {
		return this.logging;
	}

	public BigDecimal getMaxExtents() {
		return this.maxExtents;
	}

	public BigDecimal getMaxTrans() {
		return this.maxTrans;
	}

	public BigDecimal getMinExtents() {
		return this.minExtents;
	}

	public String getMonitoring() {
		return this.monitoring;
	}

	public String getNested() {
		return this.nested;
	}

	public BigDecimal getNextExtent() {
		return this.nextExtent;
	}

	public BigDecimal getNumFreelistBlocks() {
		return this.numFreelistBlocks;
	}

	public BigDecimal getNumRows() {
		return this.numRows;
	}

	public String getOwner() {
		return this.owner;
	}

	public String getPartitioned() {
		return this.partitioned;
	}

	public BigDecimal getPctFree() {
		return this.pctFree;
	}

	public BigDecimal getPctIncrease() {
		return this.pctIncrease;
	}

	public BigDecimal getPctUsed() {
		return this.pctUsed;
	}

	public String getReadOnly() {
		return this.readOnly;
	}

	public String getResultCache() {
		return this.resultCache;
	}

	public String getRowMovement() {
		return this.rowMovement;
	}

	public BigDecimal getSampleSize() {
		return this.sampleSize;
	}

	public String getSecondary() {
		return this.secondary;
	}

	public String getSegmentCreated() {
		return this.segmentCreated;
	}

	public String getSkipCorrupt() {
		return this.skipCorrupt;
	}

	public String getStatus() {
		return this.status;
	}

	public String getTableLock() {
		return this.tableLock;
	}

	public String getTableName() {
		return this.tableName;
	}

	public String getTablespaceName() {
		return this.tablespaceName;
	}

	public String getTemporary() {
		return this.temporary;
	}

	public String getUserStats() {
		return this.userStats;
	}

	public void setAvgRowLen(BigDecimal avgRowLen) {
		this.avgRowLen = avgRowLen;
	}

	public void setAvgSpace(BigDecimal avgSpace) {
		this.avgSpace = avgSpace;
	}

	public void setAvgSpaceFreelistBlocks(BigDecimal avgSpaceFreelistBlocks) {
		this.avgSpaceFreelistBlocks = avgSpaceFreelistBlocks;
	}

	public void setBackedUp(String backedUp) {
		this.backedUp = backedUp;
	}

	public void setBlocks(BigDecimal blocks) {
		this.blocks = blocks;
	}

	public void setBufferPool(String bufferPool) {
		this.bufferPool = bufferPool;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public void setCellFlashCache(String cellFlashCache) {
		this.cellFlashCache = cellFlashCache;
	}

	public void setChainCnt(BigDecimal chainCnt) {
		this.chainCnt = chainCnt;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setClusterOwner(String clusterOwner) {
		this.clusterOwner = clusterOwner;
	}

	public void setCompressFor(String compressFor) {
		this.compressFor = compressFor;
	}

	public void setCompression(String compression) {
		this.compression = compression;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setDependencies(String dependencies) {
		this.dependencies = dependencies;
	}

	public void setDropped(String dropped) {
		this.dropped = dropped;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setEmptyBlocks(BigDecimal emptyBlocks) {
		this.emptyBlocks = emptyBlocks;
	}

	public void setFlashCache(String flashCache) {
		this.flashCache = flashCache;
	}

	public void setFreelistGroups(BigDecimal freelistGroups) {
		this.freelistGroups = freelistGroups;
	}

	public void setFreelists(BigDecimal freelists) {
		this.freelists = freelists;
	}

	public void setGlobalStats(String globalStats) {
		this.globalStats = globalStats;
	}

	public void setInitialExtent(BigDecimal initialExtent) {
		this.initialExtent = initialExtent;
	}

	public void setIniTrans(BigDecimal iniTrans) {
		this.iniTrans = iniTrans;
	}

	public void setInstances(String instances) {
		this.instances = instances;
	}

	public void setIotName(String iotName) {
		this.iotName = iotName;
	}

	public void setIotType(String iotType) {
		this.iotType = iotType;
	}

	public void setLastAnalyzed(Date lastAnalyzed) {
		this.lastAnalyzed = lastAnalyzed;
	}

	public void setLogging(String logging) {
		this.logging = logging;
	}

	public void setMaxExtents(BigDecimal maxExtents) {
		this.maxExtents = maxExtents;
	}

	public void setMaxTrans(BigDecimal maxTrans) {
		this.maxTrans = maxTrans;
	}

	public void setMinExtents(BigDecimal minExtents) {
		this.minExtents = minExtents;
	}

	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}

	public void setNested(String nested) {
		this.nested = nested;
	}

	public void setNextExtent(BigDecimal nextExtent) {
		this.nextExtent = nextExtent;
	}

	public void setNumFreelistBlocks(BigDecimal numFreelistBlocks) {
		this.numFreelistBlocks = numFreelistBlocks;
	}

	public void setNumRows(BigDecimal numRows) {
		this.numRows = numRows;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPartitioned(String partitioned) {
		this.partitioned = partitioned;
	}

	public void setPctFree(BigDecimal pctFree) {
		this.pctFree = pctFree;
	}

	public void setPctIncrease(BigDecimal pctIncrease) {
		this.pctIncrease = pctIncrease;
	}

	public void setPctUsed(BigDecimal pctUsed) {
		this.pctUsed = pctUsed;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public void setResultCache(String resultCache) {
		this.resultCache = resultCache;
	}

	public void setRowMovement(String rowMovement) {
		this.rowMovement = rowMovement;
	}

	public void setSampleSize(BigDecimal sampleSize) {
		this.sampleSize = sampleSize;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public void setSegmentCreated(String segmentCreated) {
		this.segmentCreated = segmentCreated;
	}

	public void setSkipCorrupt(String skipCorrupt) {
		this.skipCorrupt = skipCorrupt;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTableLock(String tableLock) {
		this.tableLock = tableLock;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}

	public void setTemporary(String temporary) {
		this.temporary = temporary;
	}

	public void setUserStats(String userStats) {
		this.userStats = userStats;
	}

}
