package me.kursaDarbs.app.custom;

import me.kursaDarbs.app.model.BoughtSeats;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SeatPurchaseProcessing extends Validation {
    public SeatPurchaseProcessing() {}


    public Boolean seatsAreValid(List<Integer> seatsToPurchase, List<BoughtSeats> takenSeats, Integer cols) {
        if (!takenSeats.isEmpty()) {
            int totalRows = cols;
            for (int i = 0; i < seatsToPurchase.size(); ++i) {
                int seatToBuyRow = ind2subRow(seatsToPurchase.get(i), totalRows);
                int seatToBuyCol = ind2subCol(seatsToPurchase.get(i), totalRows);
                for (int j = 0; j < takenSeats.size(); ++j) {
                    int takenSeatRow = takenSeats.get(j).GetRow();
                    int takenSeatCol = takenSeats.get(j).GetCol();
                    if (seatToBuyRow == takenSeatRow && seatToBuyCol == takenSeatCol) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<List<Integer>> ind2Sub(List<Integer> monoList, Integer rows) {
        List<List<Integer>> sub = new ArrayList<List<Integer>>();
        for (int i = 0; i < monoList.size(); ++i) {
            int row = ind2subRow(monoList.get(i), rows);
            int col = ind2subCol(monoList.get(i), rows);
            List<Integer> temp = new ArrayList<>();
            temp.add(row);
            temp.add(col);
            sub.add(temp);
        }
        return sub;
    }

    public Integer ind2subRow(Integer mono, Integer rowCount) {
        return  mono / rowCount;
    }

    public Integer ind2subCol(Integer mono, Integer rowCount) {
        return  mono % rowCount;
    }




    public List<String> GenerateOrderNumber(Integer sessionId, List<List<Integer>> seats) {
        List<String> orderNumber = new ArrayList<>();
        for (int i = 0; i < seats.size(); ++i) {
            orderNumber.add(sessionId + "-" + seats.get(i).get(0) + "-" + seats.get(i).get(1));
        }
        return orderNumber;
    }


}
