-- Check the current stock count
local stockCount = tonumber(redis.call('hget', KEYS[1], ARGV[1]))

-- If stock count is less than 1, throw an error
if stockCount < 1 then
    error("Inventory cannot be less than 0. Please replenish inventory")
end

-- Decrement the stock count by 1
redis.call('hincrby', KEYS[1], ARGV[1], -1)

-- Return the updated stock count
return stockCount - 1
