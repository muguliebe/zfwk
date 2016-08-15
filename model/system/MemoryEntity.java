package zany.model.system;

import java.text.DecimalFormat;

public class MemoryEntity {

    long physicalTotal    = 0;
    long physicalUsed     = 0;
    int  physicalUsedPerc = 0;
    long physicalFree     = 0;
    int  physicalFreePerc = 0;
    long swapTotal        = 0;
    long swapUsed         = 0;
    int  swapUsedPerc     = 0;
    long swapFree         = 0;
    int  swapFreePerc     = 0;

    // ==========================================================================
    // Physical Total
    // ==========================================================================
    public long getPhysicalTotal() {
        return physicalTotal;
    }

    public void setPhysicalTotal(long physicalTotal) {
        this.physicalTotal = physicalTotal;
    }

    // ==========================================================================
    // Physical Used
    // ==========================================================================
    public long getPhysicalUsed() {
        return physicalUsed;
    }

    public void setPhysicalUsed(long physicalUsed) {
        this.physicalUsed = physicalUsed;
        setPhysicalUsedPerc();
    }

    public int getPhysicalUsedPerc() {
        return physicalUsedPerc;
    }

    private void setPhysicalUsedPerc() {
        if (physicalTotal > 0) {
            physicalUsedPerc = Integer.parseInt(new DecimalFormat("##").format(new Double((double) physicalUsed / (double) physicalTotal * 100)));
        }
    }


    // ==========================================================================
    // Physical Free
    // ==========================================================================
    public long getPhysicalFree() {
        return physicalFree;
    }

    public void setPhysicalFree(long physicalFree) {
        this.physicalFree = physicalFree;
        setPhysicalFreePerc();
    }

    public int getPhysicalFreePerc() {
        return physicalFreePerc;
    }

    private void setPhysicalFreePerc() {
        if (physicalTotal > 0) {
            physicalFreePerc = Integer.parseInt(new DecimalFormat("##").format(new Double((double) physicalFree / (double) physicalTotal * 100)));
        }
    }

    // ==========================================================================
    // Swap Total
    // ==========================================================================
    public long getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(long swalTotal) {
        this.swapTotal = swalTotal;
    }

    // ==========================================================================
    // Swap Used
    // ==========================================================================
    public long getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(long swapUsed) {
        this.swapUsed = swapUsed;
        setSwapUsedPerc();
    }

    public int getSwapUsedPerc() {
        return swapUsedPerc;
    }

    private void setSwapUsedPerc() {
        if (swapTotal > 0) {
            swapUsedPerc = Integer.parseInt(new DecimalFormat("##").format(new Double((double) swapUsed / (double) swapTotal * 100)));
        }
    }

    // ==========================================================================
    // Swap Free
    // ==========================================================================
    public long getSwapFree() {
        return swapFree;
    }

    public void setSwapFree(long swapFree) {
        this.swapFree = swapFree;
        setSwapFreePerc();
    }

    public int getSwapFreePerc() {
        return swapFreePerc;
    }

    private void setSwapFreePerc() {
        if (swapTotal > 0) {
            swapFreePerc = Integer.parseInt(new DecimalFormat("##").format(new Double((double) swapFree / (double) swapTotal * 100)));
        }
    }

    // ==========================================================================
    // Etc
    // ==========================================================================
    @Override
    public String toString() {
        return "MemoryEntity [physicalTotal=" + physicalTotal + ", physicalUsed=" + physicalUsed + ", physicalUsedPerc=" + physicalUsedPerc + ", physicalFree=" + physicalFree + ", physicalFreePerc="
            + physicalFreePerc + ", swapTotal=" + swapTotal + ", swapUsed=" + swapUsed + ", swapUsedPerc=" + swapUsedPerc + ", swapFree=" + swapFree + ", swapFreePerc=" + swapFreePerc + "]";
    }


}
