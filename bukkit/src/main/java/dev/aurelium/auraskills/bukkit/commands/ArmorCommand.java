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
import dev.aurelium.auraskills.api.skill.Skill;
import dev.aurelium.auraskills.api.stat.Stat;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.util.AuraSkillsModifier.Operation;
import dev.aurelium.auraskills.bukkit.AuraSkills;
import dev.aurelium.auraskills.common.message.type.ACFCoreMessage;
import org.bukkit.entity.Player;

import java.util.Locale;

@CommandAlias("%skills_alias")
@Subcommand("armor")
public class ArmorCommand extends BaseCommand {

    private final AuraSkills plugin;
    private final BaseItemCommand baseItemCommand;

    public ArmorCommand(AuraSkills plugin) {
        this.plugin = plugin;
        this.baseItemCommand = new BaseItemCommand(plugin, "ARMOR", ModifierType.ARMOR);
    }

    @Subcommand("modifier add")
    @CommandCompletion("@stats @nothing @modifier_operations false|true false|true @players")
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_modifier_add")
    public void onItemModifierAdd(CommandIssuer issuer, Stat stat, double value, @Default("add") Operation operation, @Default("true") boolean lore,
            @Default("false") boolean overwrite, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_modifier_remove")
    public void onItemModifierRemoveOther(CommandIssuer issuer, Stat stat, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_modifier_list")
    public void onItemModifierListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_modifier_removeall")
    public void onItemModifierRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_trait_add")
    public void onItemTraitAddOther(CommandIssuer issuer, Trait trait, double value, @Default("add") Operation operation, @Default("true") boolean lore,
            @Default("false") boolean overwrite, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_trait_remove")
    public void onItemTraitRemoveOther(CommandIssuer issuer, Trait trait, @Default("true") boolean lore, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_trait_list")
    public void onItemTraitListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.modifier")
    @Description("%desc_armor_trait_removeall")
    public void onItemTraitRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.modifier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.requirement")
    @Description("%desc_armor_requirement_add")
    public void onItemRequirementAddOther(CommandIssuer issuer, Skill skill, int level, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.armor.requirement.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.requirement")
    @Description("%desc_armor_requirement_remove")
    public void onItemRequirementRemoveOther(CommandIssuer issuer, Skill skill, @Default("true") boolean lore,
            @Flags("other") @CommandPermission("auraskills.command.armor.requirement.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.requirement")
    @Description("%desc_armor_requirement_list")
    public void onItemRequirementListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.requirement.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.requirement")
    @Description("%desc_armor_requirement_removeall")
    public void onItemRequirementRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.requirement.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.multiplier")
    @Description("%desc_armor_multiplier_add")
    public void onItemMultiplierAddOther(CommandIssuer issuer, String target, double value, @Default("true") boolean lore, @Default("false") boolean overwrite,
            @Flags("other") @CommandPermission("auraskills.command.armor.multiplier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.multiplier")
    @Description("%desc_armor_multiplier_remove")
    public void onItemMultiplierRemoveOther(CommandIssuer issuer, String target, @Flags("other") @CommandPermission("auraskills.command.armor.multiplier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.multiplier")
    @Description("%desc_armor_multiplier_list")
    public void onItemMultiplierListOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.multiplier.other") @Optional Player other) {
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
    @CommandPermission("auraskills.command.armor.multiplier")
    @Description("%desc_armor_multiplier_removeall")
    public void onItemMultiplierRemoveAllOther(CommandIssuer issuer, @Flags("other") @CommandPermission("auraskills.command.armor.multiplier.other") @Optional Player other) {
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

}
