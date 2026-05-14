package dev.aurelium.auraskills.bukkit.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import dev.aurelium.auraskills.api.item.ModifierType;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.skill.Skill;
import dev.aurelium.auraskills.api.stat.Stat;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.util.AuraSkillsModifier.Operation;
import dev.aurelium.auraskills.bukkit.AuraSkills;
import dev.aurelium.auraskills.bukkit.item.SkillsItem;
import dev.aurelium.auraskills.bukkit.util.ItemUtils;
import dev.aurelium.auraskills.common.message.type.ACFCoreMessage;
import dev.aurelium.auraskills.common.message.type.CommandMessage;
import dev.aurelium.auraskills.common.message.type.LevelerMessage;
import dev.aurelium.auraskills.common.user.User;
import dev.aurelium.auraskills.common.util.data.KeyIntPair;
import dev.aurelium.auraskills.common.util.text.TextUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

@CommandAlias("%skills_alias")
@Subcommand("item")
public class ItemCommand extends BaseCommand {

    private final AuraSkills plugin;
    private final BaseItemCommand baseItemCommand;

    public ItemCommand(AuraSkills plugin) {
        this.plugin = plugin;
        this.baseItemCommand = new BaseItemCommand(plugin, "ITEM", ModifierType.ITEM);
    }

    @Subcommand("modifier add")
    @CommandCompletion("@stats @nothing @modifier_operations false|true false|true @players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_modifier_add")
    public void onItemModifierAdd(CommandIssuer issuer, Stat stat, double value, @Default("add") Operation operation, @Default("true") boolean lore,
            @Default("false") boolean overwrite, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemModifierAdd(issuer, player, stat, value, operation, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemModifierAdd(issuer, other, stat, value, operation, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("modifier remove")
    @CommandCompletion("@stats false|true @players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_modifier_remove")
    public void onItemModifierRemoveOther(CommandIssuer issuer, Stat stat, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemModifierRemove(issuer, player, stat, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemModifierRemove(issuer, other, stat, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("modifier list")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_modifier_list")
    public void onItemModifierListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemModifierList(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemModifierList(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("modifier removeall")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_modifier_removeall")
    public void onItemModifierRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemModifierRemoveAll(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemModifierRemoveAll(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("trait add")
    @CommandCompletion("@traits @nothing @modifier_operations false|true false|true @players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_trait_add")
    public void onItemTraitAddOther(CommandIssuer issuer, Trait trait, double value, @Default("add") Operation operation, @Default("true") boolean lore,
            @Default("false") boolean overwrite, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemTraitAdd(issuer, player, trait, value, operation, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemTraitAdd(issuer, other, trait, value, operation, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("trait remove")
    @CommandCompletion("@traits false|true @players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_trait_remove")
    public void onItemTraitRemoveOther(CommandIssuer issuer, Trait trait, @Default("true") boolean lore, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemTraitRemove(issuer, player, trait, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemTraitRemove(issuer, other, trait, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("trait list")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_trait_list")
    public void onItemTraitListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemTraitList(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemTraitList(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("trait removeall")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.modifier")
    @Description("%desc_item_trait_removeall")
    public void onItemTraitRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.modifier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemTraitRemoveAll(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemTraitRemoveAll(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("requirement add")
    @CommandCompletion("@skills @nothing false|true @players")
    @CommandPermission("auraskills.command.item.requirement")
    @Description("%desc_item_requirement_add")
    public void onItemRequirementAddOther(CommandIssuer issuer, Skill skill, int level, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.item.requirement.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemRequirementAdd(issuer, player, skill, level, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemRequirementAdd(issuer, other, skill, level, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("requirement remove")
    @CommandCompletion("@skills false|true @players")
    @CommandPermission("auraskills.command.item.requirement")
    @Description("%desc_item_requirement_remove")
    public void onItemRequirementRemoveOther(CommandIssuer issuer, Skill skill, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.item.requirement.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemRequirementRemove(issuer, player, skill, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemRequirementRemove(issuer, other, skill, lore);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("requirement list")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.requirement")
    @Description("%desc_item_requirement_list")
    public void onItemRequirementListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.requirement.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemRequirementList(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemRequirementList(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("requirement removeall")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.requirement")
    @Description("%desc_item_requirement_removeall")
    public void onItemRequirementRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.requirement.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemRequirementRemoveAll(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemRequirementRemoveAll(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("multiplier add")
    @CommandCompletion("@skills_global @nothing true|false true|false @players")
    @CommandPermission("auraskills.command.item.multiplier")
    @Description("%desc_item_multiplier_add")
    public void onItemMultiplierAddOther(CommandIssuer issuer, String target, double value, @Default("true") boolean lore, @Default("false") boolean overwrite,
            @Flags("other") @CommandPermission("auraskills.command.item.multiplier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemMultiplierAdd(issuer, player, target, value, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemMultiplierAdd(issuer, other, target, value, lore, overwrite);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("multiplier remove")
    @CommandCompletion("@skills_global @players")
    @CommandPermission("auraskills.command.item.multiplier")
    @Description("%desc_item_multiplier_remove")
    public void onItemMultiplierRemoveOther(CommandIssuer issuer, String target, @Flags("other") @CommandPermission("auraskills.command.item.multiplier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemMultiplierRemove(issuer, player, target);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemMultiplierRemove(issuer, other, target);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("multiplier list")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.multiplier")
    @Description("%desc_item_multiplier_list")
    public void onItemMultiplierListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.multiplier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemMultiplierList(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemMultiplierList(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("multiplier removeall")
    @CommandCompletion("@players")
    @CommandPermission("auraskills.command.item.multiplier")
    @Description("%desc_item_multiplier_removeall")
    public void onItemMultiplierRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.item.multiplier.other") @Optional Player other) {
        Locale locale = plugin.getLocale(issuer);
        if (other == null) {
            if (issuer.isPlayer()) {
                Player player = issuer.getIssuer();
                baseItemCommand.onItemMultiplierRemoveAll(issuer, player);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.NOT_ALLOWED_ON_CONSOLE, locale));
            }
        } else {
            if (baseItemCommand.checkItemHeld(other)) {
                baseItemCommand.onItemMultiplierRemoveAll(issuer, other);
            } else {
                issuer.sendMessage(plugin.getMsg(ACFCoreMessage.ERROR_PERFORMING_COMMAND, locale));
            }
        }
    }

    @Subcommand("register")
    @CommandPermission("auraskills.command.item.register")
    @Description("%desc_item_register")
    public void onItemRegister(@Flags("itemheld") Player player, String key) {
        Locale locale = plugin.getUser(player).getLocale();
        if (key.contains(" ")) { // Disallow spaces in key name
            player.sendMessage(plugin.getPrefix(locale) + plugin.getMsg(CommandMessage.ITEM_REGISTER_NO_SPACES, locale));
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        if (plugin.getItemRegistry().getItem(NamespacedId.fromDefault(key)) == null) { // Check that no item has been registered on the key
            plugin.getItemRegistry().register(NamespacedId.fromDefault(key), item);
            player.sendMessage(plugin.getPrefix(locale) + TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_REGISTER_REGISTERED, locale), "{key}", key));
        } else {
            player.sendMessage(plugin.getPrefix(locale) + TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_REGISTER_ALREADY_REGISTERED, locale), "{key}", key));
        }
    }

    @Subcommand("unregister")
    @CommandPermission("auraskills.command.item.register")
    @CommandCompletion("@item_keys")
    @Description("%desc_item_unregister")
    public void onItemUnregister(Player player, String key) {
        Locale locale = plugin.getUser(player).getLocale();
        if (plugin.getItemRegistry().getItem(NamespacedId.fromDefault(key)) != null) { // Check that there is an item registered on the key
            plugin.getItemRegistry().unregister(NamespacedId.fromDefault(key));
            player.sendMessage(plugin.getPrefix(locale) + TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_UNREGISTER_UNREGISTERED, locale), "{key}", key));
        } else {
            player.sendMessage(plugin.getPrefix(locale) + TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_UNREGISTER_NOT_REGISTERED, locale), "{key}", key));
        }
    }

    @Subcommand("give")
    @CommandPermission("auraskills.command.item.give")
    @CommandCompletion("@players @item_keys")
    @Description("%desc_item_give")
    public void onItemGive(CommandSender sender, @Flags("other") Player player, String key, @Default("-1") int amount) {
        ItemStack item = plugin.getItemRegistry().getItem(NamespacedId.fromDefault(key));
        Locale locale = plugin.getLocale(sender);
        if (item != null) {
            if (amount != -1) {
                item.setAmount(amount);
            }
            plugin.getScheduler().executeAtEntity(player, (task) -> {
                ItemStack leftoverItem = ItemUtils.addItemToInventory(player, item);

                String senderMsg = TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_GIVE_SENDER, locale),
                        "{amount}", String.valueOf(item.getAmount()), "{key}", key, "{player}", player.getName());
                if (!senderMsg.isEmpty()) {
                    sender.sendMessage(plugin.getPrefix(locale) + senderMsg);
                }

                if (!sender.equals(player)) {
                    String message = TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_GIVE_RECEIVER, locale),
                            "{amount}", String.valueOf(item.getAmount()), "{key}", key);
                    if (!message.isEmpty()) {
                        player.sendMessage(plugin.getPrefix(locale) + message);
                    }
                }
                // Add to unclaimed items if leftover
                if (leftoverItem != null) {
                    User user = plugin.getUser(player);
                    user.getUnclaimedItems().add(new KeyIntPair(key, leftoverItem.getAmount()));

                    String message = plugin.getMsg(LevelerMessage.UNCLAIMED_ITEM, locale);
                    if (!message.isEmpty()) {
                        player.sendMessage(plugin.getPrefix(locale) + message);
                    }
                }
            });
        } else {
            sender.sendMessage(plugin.getPrefix(locale) + TextUtil.replace(plugin.getMsg(CommandMessage.ITEM_UNREGISTER_NOT_REGISTERED, locale), "{key}", key));
        }
    }

    @Subcommand("ignore add")
    @CommandPermission("auraskills.command.item.ignore")
    @Description("%desc_item_ignore_add")
    public void onItemIgnoreAdd(@Flags("itemheld") Player player) {
        Locale locale = plugin.getUser(player).getLocale();

        ItemStack item = player.getInventory().getItemInMainHand();
        SkillsItem skillsItem = new SkillsItem(item, plugin);
        skillsItem.addIgnore();
        item = skillsItem.getItem();

        player.getInventory().setItemInMainHand(item);
        player.sendMessage(plugin.getPrefix(locale) + plugin.getMsg(CommandMessage.ITEM_IGNORE_ADD_ADDED, locale));
    }

    @Subcommand("ignore remove")
    @CommandPermission("auraskills.command.item.ignore")
    @Description("%desc_item_ignore_remove")
    public void onItemIgnoreRemove(@Flags("itemheld") Player player) {
        Locale locale = plugin.getUser(player).getLocale();

        ItemStack item = player.getInventory().getItemInMainHand();
        SkillsItem skillsItem = new SkillsItem(item, plugin);
        skillsItem.removeIgnore();
        item = skillsItem.getItem();

        player.getInventory().setItemInMainHand(item);
        player.sendMessage(plugin.getPrefix(locale) + plugin.getMsg(CommandMessage.ITEM_IGNORE_REMOVE_REMOVED, locale));
    }

}
