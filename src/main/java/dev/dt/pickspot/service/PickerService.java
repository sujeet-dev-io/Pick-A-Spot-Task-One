package dev.dt.pickspot.service;

import dev.dt.pickspot.model.Container;
import dev.dt.pickspot.model.Slot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PickerService {

    private static final int INVALID = 10_000;

    public Optional<Slot> chooseBestSlot(Container c, List<Slot> slots) {
        Slot best = null;
        int bestScore = INVALID + 1;

        for (Slot s : slots) {
            int score = score(c, s);
            if (score < bestScore) {
                bestScore = score;
                best = s;
            }
        }
        return bestScore >= INVALID ? Optional.empty() : Optional.of(best);
    }

    private int score(Container c, Slot s) {
        int distance = Math.abs(c.x() - s.x()) + Math.abs(c.y() - s.y());
        int sizePenalty = (c.size().equals("big") && s.sizeCap().equals("small")) ? INVALID : 0;
        int coldPenalty = (c.needsCold() && !s.hasColdUnit()) ? INVALID : 0;
        int occPenalty = s.occupied() ? INVALID : 0;

        return distance + sizePenalty + coldPenalty + occPenalty;
    }
}
