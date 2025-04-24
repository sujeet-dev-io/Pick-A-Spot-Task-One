package dev.dt.pickspot.serviceTestCase;

import dev.dt.pickspot.model.Container;
import dev.dt.pickspot.model.Slot;
import dev.dt.pickspot.service.PickerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickerServiceTest {
    private final PickerService picker = new PickerService();

    @Test
    void yardFullReturnsEmpty() {
        Container c = new Container("X", "small", false, 0, 0);
        List<Slot> full = List.of(new Slot(0, 1, "small", false, true));
        assertTrue(picker.chooseBestSlot(c, full).isEmpty());
    }
}
