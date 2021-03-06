package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.RangeRequest;
import com.flextrade.jfixture.utility.SpecimenType;

class NumericRangeRelay implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof RangeRequest)) {
            return new NoSpecimen();
        }

        RangeRequest rangeRequest = (RangeRequest) request;
        if (!requestIsAMatch(rangeRequest)) {
            return new NoSpecimen();
        }

        return create(rangeRequest, context);
    }

    private Object create(RangeRequest request, SpecimenContext context) {
        NumberInRangeGenerator numberInRangeGenerator = getNumberInRangeGenerator(request);
        return numberInRangeGenerator.create(request.getRequest(), context);
    }

    private boolean requestIsAMatch(RangeRequest request) {
        boolean isType = request.getRequest() instanceof SpecimenType;
        if (!isType) return false;

        SpecimenType type = (SpecimenType) request.getRequest();
        return Number.class.isAssignableFrom(type.getRawType()) &&
               request.getMin() instanceof Number &&
               request.getMax() instanceof Number;
    }

    private NumberInRangeGenerator getNumberInRangeGenerator(RangeRequest request) {
        Long min = ((Number) request.getMin()).longValue();
        Long max = ((Number) request.getMax()).longValue();

        return new NumberInRangeGenerator(min, max);
    }
}
