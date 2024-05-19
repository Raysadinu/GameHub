package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.entities.Games.Category;
import com.gamehub2.gamehub.entities.Games.Game;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CategoryBean {
    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(CategoryBean.class.getName());

    public List<CategoryDto> findAllCategories() {
        LOG.info("\n** Entered findAllCategories method **\n");
        try {
            TypedQuery<Category> typedQuery = entityManager.createQuery("SELECT c FROM Category c", Category.class);
            List<Category> categoryList = typedQuery.getResultList();
            LOG.info("\n** Exited findAllCategories method **\n");
            return copyCategoriesToDto(categoryList);
        } catch (Exception ex) {
            LOG.severe("\nError in findAllCategories method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    private List<CategoryDto> copyCategoriesToDto(List<Category> categoryList) {
        LOG.info("\n** Entered copyCategoriesToDto method with list size of: " + categoryList.size() + " **\n");

        List<CategoryDto> listToReturn = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryDto categoryDtoTemp = new CategoryDto();
            categoryDtoTemp.setCategoryId(category.getCategoryId());
            categoryDtoTemp.setCategoryName(category.getCategoryName());
            categoryDtoTemp.setGameIds(category.getGames().stream().map(game -> game.getGameId()).toList());
            listToReturn.add(categoryDtoTemp);
        }

        LOG.info("\n** Exited copyCategoriesToDto method. **\n");
        return listToReturn;
    }

    public CategoryDto getCategoryById(Long categoryId) {
        LOG.info("\n** Entered getCategoryById method for the categoryId: " + categoryId + " **\n");

        Category category = entityManager.find(Category.class, categoryId);
        if (category == null) {
            LOG.info("\n** No category found, returning NULL CATEGORY **\n");
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setGameIds(category.getGames().stream().map(Game::getGameId).toList());

        LOG.info("\n** Exited getCategoryById method. **\n");
        return categoryDto;
    }

    public List<CategoryDto> getCategoriesByGameId(Long gameId) {
        LOG.info("\n** Entered getCategoriesByGameId method for gameId: " + gameId + " **\n");
        try {
            TypedQuery<Category> query = entityManager.createQuery(
                    "SELECT c FROM Category c JOIN c.games g WHERE g.gameId = :gameId", Category.class);
            query.setParameter("gameId", gameId);
            List<Category> categoryList = query.getResultList();
            LOG.info("\n** Exited getCategoriesByGameId method **\n");
            return copyCategoriesToDto(categoryList);
        } catch (Exception ex) {
            LOG.severe("\nError in getCategoriesByGameId method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }


}
