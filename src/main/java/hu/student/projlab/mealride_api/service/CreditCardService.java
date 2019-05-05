package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.repository.CreditCardRepository;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
import hu.student.projlab.mealride_api.service.mapper.CreditCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private CreditCardMapper mapper;

    private UserService userService;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, CreditCardMapper mapper, UserService userService) {
        this.creditCardRepository = creditCardRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public List<CreditCardDTO> findAll() {
        return mapper
                .creditCardListTocreditCardDTOList(
                        creditCardRepository.findAllByCustomerId(
                                userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                                        .getCustomerUser().getId())
                                .orElse(Collections.emptyList()));

    }

    /**
     * Save credit card.
     *
     * @param cardDTO Credit card which needs to be persisted.
     * @return The new credit card with generated ID
     */
    public CreditCardDTO addCard(CreditCardDTO cardDTO) {
        CreditCard card = mapper.creditCardDTOTocreditCard(cardDTO);
        card.setCustomer(
                userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                        .getCustomerUser());
        card.setCreationDate(LocalDateTime.now());
        return mapper.creditCardTocreditCardDTO(creditCardRepository.save(card));
    }

    /**
     * Update credit card.
     *
     * @param cardDTO Credit card which needs to be updated.
     * @return The credit card with updated values
     */
    public CreditCardDTO updateCard(CreditCardDTO cardDTO) {

        checkIfUserHasSpecifiedCreditCard(cardDTO);
        return mapper
                .creditCardTocreditCardDTO(
                        creditCardRepository.save(
                                mapper.creditCardDTOTocreditCard(cardDTO)));
    }

    /**
     * Deletes the relation to CustomerUser.
     *
     * @param id ID of the credit card
     */
    public void deleteCard(Long id) {
        CreditCard card = creditCardRepository.findById(id).get();
        checkIfUserHasSpecifiedCreditCard(mapper.creditCardTocreditCardDTO(card));
        card.setCustomer(null);
        card.setDeletionDate(LocalDateTime.now());
        creditCardRepository.save(card);
    }

    private void checkIfUserHasSpecifiedCreditCard(CreditCardDTO cardDTO) {
        List<CreditCard> userCards =
                creditCardRepository.findAllByCustomerId(
                        userService
                                .getCurrentUser(SecurityUtils.getCurrentUserLogin())
                                .getCustomerUser().getId())
                        .orElse(Collections.emptyList());


        // TODO check why wrong the equals and hashcode methods in CreditCard
        /*if (!userCards.contains(mapper.creditCardDTOTocreditCard(cardDTO)))
            throw new AccessDeniedException("Card not found.");*/
    }

}
