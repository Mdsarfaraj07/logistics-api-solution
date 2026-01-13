package com.logistics.optimizer.service;
import com.logistics.optimizer.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LoadOptimizerService {
    public OptimizationResponse solve(OptimizationRequest req) {
        Truck t = req.truck(); List<Order> ords = req.orders(); int n = ords.size();
        long[] dp = new long[1 << n]; Arrays.fill(dp, -1); dp[0] = 0;
        int bestMask = 0; long maxPayout = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == -1) continue;
            int curW = 0, curV = 0;
            for (int i = 0; i < n; i++) if ((mask & (1 << i)) != 0) {
                curW += ords.get(i).weight_lbs(); curV += ords.get(i).volume_cuft();
            }
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    Order next = ords.get(i);
                    if (curW + next.weight_lbs() <= t.max_weight_lbs() && curV + next.volume_cuft() <= t.max_volume_cuft() && !next.pickup_date().isAfter(next.delivery_date())) {
                        int nextM = mask | (1 << i); long nextP = dp[mask] + next.payout_cents();
                        if (nextP > dp[nextM]) { dp[nextM] = nextP; if (nextP > maxPayout) { maxPayout = nextP; bestMask = nextM; } }
                    }
                }
            }
        }
        return buildResponse(bestMask, ords, t, maxPayout);
    }
    private OptimizationResponse buildResponse(int mask, List<Order> ords, Truck t, long payout) {
        List<String> ids = new ArrayList<>(); int w = 0, v = 0;
        for (int i = 0; i < ords.size(); i++) if ((mask & (1 << i)) != 0) { ids.add(ords.get(i).id()); w += ords.get(i).weight_lbs(); v += ords.get(i).volume_cuft(); }
        return new OptimizationResponse(t.id(), ids, payout, w, v, (w * 100.0) / t.max_weight_lbs(), (v * 100.0) / t.max_volume_cuft());
    }
}
