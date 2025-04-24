package dev.dt.pickspot.dto;

import dev.dt.pickspot.model.Container;
import dev.dt.pickspot.model.Slot;

import java.util.List;

public record PickRequest(Container container, List<Slot> yardMap) {}
