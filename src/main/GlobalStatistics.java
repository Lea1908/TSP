package main;

public class GlobalStatistics {
    Double averageCalculationTime;
    Double shortestCalulationTime;
    Double longestCalculationTime;
    // todo think about other possible informations

    public GlobalStatistics() {}
    public GlobalStatistics(Double averageCalculationTime, Double shortestCalulationTime, Double longestCalculationTime) {
        this.averageCalculationTime = averageCalculationTime;
        this.shortestCalulationTime = shortestCalulationTime;
        this.longestCalculationTime = longestCalculationTime;
    }

    public Double getAverageCalculationTime() {
        return averageCalculationTime;
    }

    public void setAverageCalculationTime(Double averageCalculationTime) {
        this.averageCalculationTime = averageCalculationTime;
    }

    public Double getShortestCalulationTime() {
        return shortestCalulationTime;
    }

    public void setShortestCalulationTime(Double shortestCalulationTime) {
        this.shortestCalulationTime = shortestCalulationTime;
    }

    public Double getLongestCalculationTime() {
        return longestCalculationTime;
    }

    public void setLongestCalculationTime(Double longestCalculationTime) {
        this.longestCalculationTime = longestCalculationTime;
    }
}
