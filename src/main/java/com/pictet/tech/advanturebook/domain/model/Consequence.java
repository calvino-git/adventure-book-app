package com.pictet.tech.advanturebook.domain.model;

import com.pictet.tech.advanturebook.domain.enums.ConsequenceType;

public record Consequence(ConsequenceType type, int value, String text) {
}
