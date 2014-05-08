package com.onboard.plugin.git;

import java.util.List;

import com.onboard.plugin.git.model.Push;

/**
 * {@link Push} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface PushService {
    /**
     * Get item by id
     * 
     * @param id
     * @return item
     */
    Push getPushById(int id);

    /**
     * Get item list
     * 
     * @param start
     * @param limit
     * @return the item list
     */
    List<Push> getPushes(int start, int limit);

    /**
     * Get item list by example
     * 
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<Push> getPushesByExample(Push item, int start, int limit);

    /**
     * Get item count by example
     * 
     * @param item
     * @return the count
     */
    int countByExample(Push item);

    /**
     * Create
     * 
     * @param item
     * @return the created Push
     */
    Push createPush(Push item);

    /**
     * Update
     * 
     * @param item
     * @return the updated item
     */
    Push updatePush(Push item);

    /**
     * Delete
     * 
     * @param id
     */
    void deletePush(int id);

    /**
     * 为Push填充Commits
     * 
     * @param push
     */
    void fillCommitsForPush(Push push);

    /**
     * 为Push列表填充Commits
     * 
     * @param push
     */
    void fillCommitsForPushs(List<Push> pushs);
}
