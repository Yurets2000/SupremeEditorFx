package com.yube.validation.minijava;

import com.yube.validation.*;
import com.yube.validation.exceptions.TokenResolutionException;

import java.util.*;

public class MiniJavaParser extends Parser {

    public MiniJavaParser(RuleContext ruleContext) {
        super(ruleContext);
    }

    @Override
    public List<Token> translate(List<Lexem> lexems) {
        return null;
    }

    private Token tryFormToken(String name, ParsingRule rule, List<Lexem> lexems) {
        Token token = null;
        switch (name) {
            case "PROGRAM_DECLARATION":
                break;
            case "CLASS_DECLARATION":
                break;
            // other
        }
        return token;
    }

    private ParsingResult parseThroughRule(Rule rule, List<Lexem> lexems) {
        LinkedHashMap<String, ParsingRule> prMap = ruleContext.getParsingRuleMap();
        if(rule instanceof LexingRule) {
            Optional<Lexem> lexemOptional = lexems.stream().filter(l -> l.getName().equals(rule.getValue())).findFirst();
            if (lexemOptional.isPresent()) {
                if (lexems.size() > 1) return new ParsingResult(false, null);
                Lexem lexem = lexemOptional.get();
                lexems.remove(lexem);
                return new ParsingResult(true, Collections.singletonList(lexem));
            } else {
                throw new TokenResolutionException("Rule doesn't match any lexem");
            }
        } else if (rule instanceof ParsingRule) {
            ParsingRule parsingRule = (ParsingRule) rule;
            if (parsingRule.getType() == ParsingRule.RuleType.ONE) {
                if (prMap.containsKey(rule.getValue())) { //Rule refers to other Rule
                    ParsingRule refRule = prMap.get(rule.getValue());
                    Token result = tryFormToken(rule.getValue(), refRule, lexems);
                    if (result == null) return new ParsingResult(false, null);
                    return new ParsingResult(true, Collections.singletonList(result));
                } else {
                    throw new TokenResolutionException("Rule doesn't match any other rule");
                }
            } else if (parsingRule.getType() == ParsingRule.RuleType.OR) { //Return first matched tokens
                for (Rule component : parsingRule.getRuleComponents()) {
                    ParsingResult result = parseThroughRule(component, lexems);
                    if(result.isSuccess()) {
                        return result;
                    }
                }
                return new ParsingResult(false, null);
            } else if (parsingRule.getType() == ParsingRule.RuleType.AND) { //Return all tokens
                List<Token> tokensCollector = new ArrayList<>();
                for (Rule component : parsingRule.getRuleComponents()) {
                    ParsingResult result = parseThroughRule(component, lexems);
                    if(!result.isSuccess()) {
                        return new ParsingResult(false, null);
                    } else {
                        tokensCollector.addAll(result.getTokens());
                    }
                }
                return new ParsingResult(true, tokensCollector);
            } else if (parsingRule.getType() == ParsingRule.RuleType.GROUP_ONE) {
                Rule component = parsingRule.getRuleComponents().get(0);
                return parseThroughRule(component, lexems);
            } else if (parsingRule.getType() == ParsingRule.RuleType.GROUP_ZERO_OR_ONE) {
                Rule component = parsingRule.getRuleComponents().get(0);
                ParsingResult result = parseThroughRule(component, lexems);
                if(result.isSuccess()) return result;
                return new ParsingResult(true, null);
            }
        }
        return null;
    }
}
