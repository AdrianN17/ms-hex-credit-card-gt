package com.bank.credit_card.infraestructure.web.api.mapper.command;

import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.application.port.in.command.CardCreateCommand;
import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;
import com.bank.credit_card.infraestructure.web.api.schema.request.CardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.CardResponse;
import com.bank.credit_card.infraestructure.web.api.schema.response.CardResponseAccount;
import com.bank.credit_card.infraestructure.web.api.schema.response.CardResponseBalance;
import com.bank.credit_card.infraestructure.web.api.schema.response.CardResponseBenefit;

public class CardApiMapperRequestCommandImpl implements CardApiMapperRequestCommand {

    @Override
    public CardCreateCommand toCommand(CardRequest request) {
        return new CardCreateCommand(
                TypeCardEnum.valueOf(request.getTypeCard()),
                CategoryCardEnum.valueOf(request.getCategoryCard()),
                request.getAccount().getCreditTotal(),
                request.getAccount().getDebtTax(),
                request.getBenefit().getHasDiscount(),
                request.getBenefit().getMultiplierPoints(),
                Short.parseShort(request.getAccount().getPaymentDate()),
                CurrencyEnum.valueOf(request.getAccount().getCurrency())
        );
    }

    @Override
    public CardResponse toResponse(LoadCardBalanceBenefitView view) {


        CardResponseBenefit cardResponseBenefit = new CardResponseBenefit(
                view.hasDiscount(),
                view.multiplierPoints(),
                view.totalPoint()
        );

        CardResponseBalance cardResponseBalance = new CardResponseBalance(
                view.total(),
                view.old(),
                view.available(),
                view.startDate(),
                view.endDate()
        );

        CardResponseAccount cardResponseAccount = new CardResponseAccount(
                view.creditTotal(),
                view.debtTax(),
                view.currency().name(),
                view.paymentDate().toString(),
                view.cardStatus().name()
        );

        return new CardResponse(
                view.typeCard().name(),
                view.categoryCard().name(),
                cardResponseBenefit,
                cardResponseBalance,
                cardResponseAccount
        );
    }

    @Override
    public CardCloseCommand toCommandId(Long cardId) {
        return new CardCloseCommand(cardId);
    }
}
